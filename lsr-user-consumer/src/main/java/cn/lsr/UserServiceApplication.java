package cn.lsr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("cn.lsr.user.mapper.**")
@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
        Logger logger = LoggerFactory.getLogger(UserServiceApplication.class);
        logger.info("*******************************");
        logger.info("**** 启动 user-service 成功 ****");
        logger.info("*******************************");
    }
}
