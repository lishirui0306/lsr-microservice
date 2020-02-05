package cn.lsr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * @AllArgsConstructor 注解：表示生成带有所有属性的构造方法
 * @NoArgsConstructor 注解：表示生成不带参数的构方法
 * @Data 注解：表示生成get和set方法
 */
public class Order {
    private int id;
    private String name;
    private Double price;
    private String dbSource;
}
