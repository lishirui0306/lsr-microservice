package cn.lsr.mvc.annotation;

import java.lang.annotation.*;
/**
 * @Description: 持久化层注解
 * @Package: lsr-microservice
 * @email: Hacker_lsr@126.com
 * @author: lishirui
 **/
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LSRRepository {
    public String value();
}
