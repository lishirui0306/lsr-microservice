package cn.lsr.core.druid.annotation;

import cn.lsr.core.druid.common.DataSourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 数据源切换注解
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DS {
    DataSourceType value() default DataSourceType.db1;
}
