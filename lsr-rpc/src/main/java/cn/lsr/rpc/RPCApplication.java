package cn.lsr.rpc;

import cn.lsr.rpc.demo.HelloService;
import cn.lsr.rpc.factory.ProxyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: 启动类
 * @Package: lsr-microservice
 * @email: Hacker_lsr@126.com
 * @author: lishirui
 **/
@SpringBootApplication
public class RPCApplication {
    private static final Logger log = LoggerFactory.getLogger(RPCApplication.class);
    public static void main(String[] args) throws Exception {
        SpringApplication.run(RPCApplication.class, args);
        HelloService helloService = ProxyFactory.create(HelloService.class);
        log.info("响应结果“: {}",helloService.hello("rpc"));
    }
}
