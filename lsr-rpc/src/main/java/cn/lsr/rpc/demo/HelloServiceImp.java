package cn.lsr.rpc.demo;

import org.springframework.stereotype.Service;

/**
 * @Description: 测试服务实现
 * @Package: lsr-microservice
 * @email: Hacker_lsr@126.com
 * @author: lishirui
 **/
@Service
public class HelloServiceImp implements HelloService {
    @Override
    public String hello(String name) {
        return "我是服务hello:"+name;
    }
}
