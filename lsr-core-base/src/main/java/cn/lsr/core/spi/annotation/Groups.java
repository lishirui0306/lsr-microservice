package cn.lsr.core.spi.annotation;

import java.lang.annotation.*;

/**
 * @Description: 分组注解
 * @Package: lsr-microservice
 * @email: Hacker_lsr@126.com
 * @author: lishirui
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Groups {
    String[] value() default {};
}
