package cn.lsr.core;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;

/**
 * mybatis sql 耗时
 */
@Component
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class }) })
public class MybatisInterceptor implements Interceptor {
    private static final Logger log = LoggerFactory.getLogger(MybatisInterceptor.class);
    /**
     * 执行逻辑
     *
     * @param arg0
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation arg0) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) arg0.getArgs()[0];
        Object parameter = null;
        // 获取参数，if语句成立，表示sql语句有参数，参数格式是map形式
        if (arg0.getArgs().length > 1) {
            parameter = arg0.getArgs()[1];
        }
        String sqlId = mappedStatement.getId();// 获取到节点的id,即sql语句的id
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);// BoundSql就是封装myBatis最终产生的sql类
        Configuration configuration = mappedStatement.getConfiguration();// 获取节点的配置
        Object returnValue = null;
        long start = System.currentTimeMillis();
        returnValue = arg0.proceed();
        long end = System.currentTimeMillis();
        if (log.isDebugEnabled()) { // debug 模式
            long time = (end - start);
            String sql = getSql(configuration, boundSql, sqlId, time);// 获取到最终的sql语句
            log.debug(sql);
        }
        return returnValue;
    }

    /**
     * 封装了一下sql语句，使得结果返回完整xml路径下的sql语句节点id + sql语句  + 耗时
     * @param configuration
     * @param boundSql
     * @param sqlId
     * @param time
     * @return
     */
    public static String getSql(Configuration configuration, BoundSql boundSql, String sqlId, long time) {
        String sql = showSql(configuration,boundSql);
        StringBuilder str = new StringBuilder(100);
        str.append("execute sql:");
        str.append(sql);
        str.append(",cost :");
        str.append(time);
        str.append("ms");
        return str.toString();
    }

    /**
     * 组装sql 进行？的替换 去除了 打印包名.方法名
     * @param configuration
     * @param boundSql
     * @return
     */
    public static String showSql(Configuration configuration,BoundSql boundSql) {
        //  获取参数
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        // sql语句中多个空格都用一个空格代替
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            // MetaObject主要是封装了originalObject对象，提供了get和set的方法用于获取和设置originalObject的属性值,主要支持对JavaBean、Collection、Map三种类型对象的操作
            MetaObject metaObject = configuration.newMetaObject(parameterObject);
            for (ParameterMapping parameterMapping : parameterMappings) {
                String propertyName = parameterMapping.getProperty();
                if (metaObject.hasGetter(propertyName)) {
                    Object obj = metaObject.getValue(propertyName);
                    sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                } else if (boundSql.hasAdditionalParameter(propertyName)) {
                    Object obj = boundSql.getAdditionalParameter(propertyName); // 该分支是动态sql
                    sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                }
            }
        }
        return sql;
    }
    /**
     *  如果参数是String，则添加单引号， 如果是日期，则转换为时间格式器并加单引号； 对参数是null和不是null的情况作了处理
     * @param obj
     * @return
     */
    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
