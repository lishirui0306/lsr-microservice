package cn.lsr.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@EnableCircuitBreaker
@MapperScan(basePackages = {"cn.lsr.user.mapper.**"})
@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(UserServiceApplication.class, args);
        Logger logger = LoggerFactory.getLogger(UserServiceApplication.class);
        logger.info("*******************************");
        logger.info("**** 启动 user-service 成功 ****");
        logger.info("*******************************");
    }
}
