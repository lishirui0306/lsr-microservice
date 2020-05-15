package cn.lsr.mvc.annotation;

import java.lang.annotation.*;

/**
 * @Description: 地址映射注解
 * @Package: lsr-microservice
 * @email: Hacker_lsr@126.com
 * @author: lishirui
 **/
@Documented //JavaDoc
@Target({ElementType.TYPE,ElementType.METHOD}) //作用于类及方法上
@Retention(RetentionPolicy.RUNTIME)//生命周期
public @interface LSRRequestMapping {
    public String value();
}
