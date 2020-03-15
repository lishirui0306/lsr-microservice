package cn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description: Batch启动类
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
@EnableBatchProcessing
@EnableScheduling
@SpringBootApplication
public class BatchServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BatchServiceApplication.class, args);
        Logger logger = LoggerFactory.getLogger(BatchServiceApplication.class);
        logger.info("********************************");
        logger.info("**** 启动 batch-service 成功 ****");
        logger.info("********************************");
    }
}
