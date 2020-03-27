package cn.lsr.core.druid.aspectimp;

import cn.lsr.core.druid.annotation.DS;
import cn.lsr.core.druid.common.DataSourceType;
import cn.lsr.core.druid.config.DynamicDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description: 动态数据源AOP
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
@Aspect
@Order(1)
@Component
public class DynamicDataSourceAspect {
    @Before("@annotation(ds)")
    public void beforeSwitchDS(JoinPoint joinPoint,DS ds){
        //获取当前访问的class
        Class<?> className = joinPoint.getTarget().getClass();
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        //获取方法的参数类型
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Class[] arrClass = methodSignature.getParameterTypes();
        //默认数据源
        DataSourceType dataSourceType = DataSourceType.db1;
        try {
            //得到方法对象
            Method method = className.getMethod(methodName, arrClass);
            //判断是否有注解存在
            if (method.isAnnotationPresent(DS.class)){
                //拿到注解方法
                DS dsValue = method.getAnnotation(DS.class);
                //拿到注解value
                dataSourceType = dsValue.value();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        //切换数据源
        DynamicDataSource.setDataSource(dataSourceType);
    }
    @After("@annotation(ds)")
    public void afterSwitchDS(JoinPoint joinPoint,DS ds){
        DynamicDataSource.clearDataSource();
    }
}
