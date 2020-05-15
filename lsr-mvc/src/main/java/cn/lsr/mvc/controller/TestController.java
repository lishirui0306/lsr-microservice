package cn.lsr.mvc.controller;

import cn.lsr.mvc.annotation.LSRController;
import cn.lsr.mvc.annotation.LSRQualifier;
import cn.lsr.mvc.annotation.LSRRequestMapping;
import cn.lsr.mvc.service.TestService;

/**
 * @Description: 测试控制器
 * @Package: lsr-microservice
 * @email: Hacker_lsr@126.com
 * @author: lishirui
 **/
@LSRController("testController")
@LSRRequestMapping("/test")
public class TestController {
    @LSRQualifier("testService")
    private TestService service;
    @LSRRequestMapping("/method")
    public void test(){
        service.test();
    }
}
