package cn.lsr.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot入口类,此类需要在所有用到的package上层
 * exclude = {DataSourceAutoConfiguration.class}
 * 禁用springboot默认加载的 application-dev.properties/application.yml 单数据源配置
 * @author
 */

@SpringBootApplication
public class RPCApplication {
    public static void main(String[] args) {
        SpringApplication.run(RPCApplication.class, args);
    }
}
