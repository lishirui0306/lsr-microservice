package cn.lsr.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description: 用户信息
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
@Entity
@Table(name = "user_info")
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id ;
    @Column(name = "userId")
    private Integer userId;
    @Column(name = "name")
    private String name ;
    @Column(name = "age")
    private Integer age;
    @Column(name = "description")
    private String description;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
