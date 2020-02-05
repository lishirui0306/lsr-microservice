package cn.lsr.user.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * = =  接口使用feign调用order-service的方法
 *
 * @Version: 1.0
 * @Author: Hacker_lsr@126.com
 */
@FeignClient(value = "order-service", fallback = OrderRemoteHystrix.class)
public interface OrderRemote {
    @GetMapping("/order/getOrderPort")
    String getOrderPort();
}
