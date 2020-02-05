package cn.lsr.Anotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Mytest {

    public String value();
    int age() default 22;
    int[] sorce();
}
