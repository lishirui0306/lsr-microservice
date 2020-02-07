package cn.lsr.user.core.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: redis锁调用配置
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 * @version: V1.0
 **/
@Configuration
public class RemoteRedisConfig {
    //远程调用的consul 注册的服务名
    @Value("${remote.redis.server.name}")
    private String redisLockName;
    //远程调用的consul 注册的服务名
    @Value("${remote.redis.server.getLockUrl}")
    private String getLockUrl;
    //远程调用的consul 注册的服务名
    @Value("${remote.redis.server.unLockUrl}")
    private String unLockUrl;

    public RemoteRedisConfig() {
    }

    public  String getRedisLockName() {
        return redisLockName;
    }

    public void setRedisLockName(String redisLockName) {
        this.redisLockName = redisLockName;
    }

    public String getGetLockUrl() {
        return getLockUrl;
    }

    public void setGetLockUrl(String getLockUrl) {
        this.getLockUrl = getLockUrl;
    }

    public String getUnLockUrl() {
        return unLockUrl;
    }

    public void setUnLockUrl(String unLockUrl) {
        this.unLockUrl = unLockUrl;
    }

}
