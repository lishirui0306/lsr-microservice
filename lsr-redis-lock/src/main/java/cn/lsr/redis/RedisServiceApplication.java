package cn.lsr.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RedisServiceApplication{

    public static void main(String[] args) {

        SpringApplication.run(RedisServiceApplication.class, args);
        Logger logger = LoggerFactory.getLogger(RedisServiceApplication.class);
        logger.info("*******************************");
        logger.info("**** 启动 redis-service 成功 ****");
        logger.info("*******************************");
    }
}
