package cn.lsr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//@MapperScan("cn.lsr.mapper")
@org.mybatis.spring.annotation.MapperScan("cn.lsr.mapper")

@EnableDiscoveryClient
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
        Logger logger = LoggerFactory.getLogger(OrderApplication.class);
        logger.info("********************************");
        logger.info("**** 启动 order-service 成功 ****");
        logger.info("********************************");
    }
}
