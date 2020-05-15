package cn.lsr.mvc.annotation;

import java.lang.annotation.*;
/**
 * @Description: controller层注解
 * @Package: lsr-microservice
 * @email: Hacker_lsr@126.com
 * @author: lishirui
 **/
@Documented //JavaDoc
@Target(ElementType.TYPE) //作用于类上
@Retention(RetentionPolicy.RUNTIME)//生命周期
public @interface LSRController {
    /**
     * 作用于该类上的一个value实行，即就是controller的名称
     * @return
     */
    public String value();
}
