package cn.lsr.user.config;
import org.springframework.stereotype.Component;

/**
 * = =
 *
 * @Version: 1.0
 * @Author: Hacker_lsr@126.com
 */
@Component
public class OrderRemoteHystrix implements OrderRemote {
    @Override
    public String getOrderPort() {
        return "order service 调用失败！";
    }
}
