package cn.lsr.user.controller;

import cn.lsr.user.config.OrderRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * = = user控制器
 *
 * @Version: 1.0
 * @Author: Hacker_lsr@126.com
 */
@RestController
@RequestMapping("/user")
public class TestUserController {
    @Autowired
    private OrderRemote orderRemote;
    @Value("${server.port}")
    String port;

    /**
     * 获取用户服务的端口
     * @return
     */
    @GetMapping("/getUserPort")
    public String getUserPort() {
        return "user-service port：" + port;
    }

    /**
     * 获取订单服务的端口
     * @return
     */
    @GetMapping("/getOrderPort")
    public String getOrderPort() {
        return "user-order-service port：" + orderRemote.getOrderPort();
    }



}
