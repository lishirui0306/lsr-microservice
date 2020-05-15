package cn.lsr.mvc.annotation;

import java.lang.annotation.*;
/**
 * @Description: 注入注解
 * @Package: lsr-microservice
 * @email: Hacker_lsr@126.com
 * @author: lishirui
 **/
@Documented
@Target(ElementType.FIELD)//作用于字段上实现注入
@Retention(RetentionPolicy.RUNTIME)
public @interface LSRQualifier {
    public String value();
}
