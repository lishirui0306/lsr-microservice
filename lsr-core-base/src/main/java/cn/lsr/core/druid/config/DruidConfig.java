package cn.lsr.core.druid.config;

import cn.lsr.core.druid.common.DataSourceType;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * = = Druid配置类
 *
 * @Version: 1.0
 * @Author: Hacker_lsr@126.com
 */
@Configuration
public class DruidConfig {
    @Autowired
    DruidSettings druidSettings;
    /**
     * 数据源db1
     * @return
     */
    //@ConfigurationProperties(prefix = "spring.datasource.db1")
    @Bean(name = "dataSource01")
    public DataSource dataSource01(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(druidSettings.getDriverClassName());
        druidDataSource.setUrl(druidSettings.getUrl());
        druidDataSource.setUsername(druidSettings.getUsername());
        druidDataSource.setPassword(druidSettings.getPassword());
        druidDataSource.setInitialSize(druidSettings.getInitialSize());
        druidDataSource.setMinIdle(druidSettings.getMinIdle());
        druidDataSource.setMaxActive(druidSettings.getMaxActive());
        druidDataSource.setMaxWait(druidSettings.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(druidSettings.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(druidSettings.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(druidSettings.getValidationQuery());
        druidDataSource.setTestWhileIdle(druidSettings.isTestWhileIdle());
        druidDataSource.setTestOnBorrow(druidSettings.isTestOnBorrow());
        druidDataSource.setTestOnReturn(druidSettings.isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(druidSettings.isPoolPreparedStatements());
        try {
            druidDataSource.setFilters(druidSettings.getFilters());
        } catch (Exception e) {
            e.printStackTrace();
        }
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(druidSettings.getMaxPoolPreparedStatementPerConnectionSize());
        druidDataSource.setUseGlobalDataSourceStat(druidSettings.isUseGlobalDataSourceStat());
        druidDataSource.setConnectionProperties(druidSettings.getConnectionProperties());
        return druidDataSource;
    }

    /**
     * 数据源db2
     * @return
     */
    //@ConfigurationProperties(prefix = "spring.datasource.db2")
    @Bean(name = "dataSource02")
    public DataSource dataSource02(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(druidSettings.getDriverClassName2());
        druidDataSource.setUrl(druidSettings.getUrl2());
        druidDataSource.setUsername(druidSettings.getUsername2());
        druidDataSource.setPassword(druidSettings.getPassword2());
        druidDataSource.setInitialSize(druidSettings.getInitialSize());
        druidDataSource.setMinIdle(druidSettings.getMinIdle());
        druidDataSource.setMaxActive(druidSettings.getMaxActive());
        druidDataSource.setMaxWait(druidSettings.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(druidSettings.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(druidSettings.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(druidSettings.getValidationQuery());
        druidDataSource.setTestWhileIdle(druidSettings.isTestWhileIdle());
        druidDataSource.setTestOnBorrow(druidSettings.isTestOnBorrow());
        druidDataSource.setTestOnReturn(druidSettings.isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(druidSettings.isPoolPreparedStatements());
        try {
            druidDataSource.setFilters(druidSettings.getFilters());
        } catch (Exception e) {
            e.printStackTrace();
        }
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(druidSettings.getMaxPoolPreparedStatementPerConnectionSize());
        druidDataSource.setUseGlobalDataSourceStat(druidSettings.isUseGlobalDataSourceStat());
        druidDataSource.setConnectionProperties(druidSettings.getConnectionProperties());

        return druidDataSource;
    }

    /**
     * 动态数据源管理
     * @return
     */
    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSource01());
        Map<Object,Object> dbMap = new HashMap<>();
        dbMap.put(DataSourceType.db1,dataSource01());
        dbMap.put(DataSourceType.db2,dataSource02());
        // targetDataSources 这里保存我们数据源配置的多个数据源）的数据源保存到resolvedDataSources
        dynamicDataSource.setTargetDataSources(dbMap);
        return dynamicDataSource;
    }

    /**
     * 将动态数据源放入事务管理器
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(){
        return new  DataSourceTransactionManager(dynamicDataSource());
    }
    /**
     *  配置监控服务器
     **/
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(
                new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>();
        // druid后台管理员用户
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        // 是否能够重置数据
        initParams.put("resetEnable", "false");

        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     *  配置web监控的过滤器
     **/
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean(
                new WebStatFilter());
        // 添加过滤规则
        bean.addUrlPatterns("/*");
        Map<String,String> initParams = new HashMap<>();
        // 忽略过滤格式
        initParams.put("exclusions","*.js,*.css,*.icon,*.png,*.jpg,/druid/*");
        bean.setInitParameters(initParams);
        return  bean;
    }

}
