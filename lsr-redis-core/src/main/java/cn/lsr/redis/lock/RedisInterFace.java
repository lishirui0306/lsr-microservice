package cn.lsr.redis.lock;


import cn.lsr.redis.utils.RedisResult;

/**
 * @Description: redis接口
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 * @version: V1.0
 **/
public interface RedisInterFace {
    /**
     * 获取锁服务
     * @param id 唯一标识
     * @param value 时间戳
     * @return
     */
    public RedisResult lock(String id, String value);

    /**
     *  解锁服务
     * @param id 唯一标识
     * @param value 时间戳
     */
    public RedisResult unlock(String id, String value);
}
