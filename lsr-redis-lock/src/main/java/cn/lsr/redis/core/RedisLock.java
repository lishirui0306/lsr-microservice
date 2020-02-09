package cn.lsr.redis.core;

import cn.lsr.redis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description: 用redis实现分布式锁
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 * @version: V1.0
 **/
@Component
public class RedisLock {
    /**
     * 不写默认使用带有@Primary的lsrLockRedisTemplate
     */
    @Resource(name = "lsrLockRedisTemplate")
    private RedisTemplate redisTemplate;
    @Resource(name = "lsrDBRedisTemplate")
    private RedisTemplate redisTemplate2;
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
    /**
     * 加锁
     * @param key id
     * @param value 时间戳
     * @return
     */
    public  boolean lock(String key, String value) {
        //setIfAbsent相当于jedis中的setnx，如果能赋值就返回true，如果已经有值了，就返回false
        //即：在判断这个key是不是第一次进入这个方法
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            //第一次，即：这个key还没有被赋值的时候
            return true;

        }
        String current_value = (String) redisTemplate.opsForValue().get(key);
        if (!StringUtils.object2String(current_value).equals("")
                //超时了
                && Long.parseLong(current_value) < System.currentTimeMillis()) {//①
            //并发 重置value 让其获得锁失败！
            redisTemplate.opsForValue().getAndSet(key, value);//②
            String newValue = (String) redisTemplate.opsForValue().get(key);
            if (!StringUtils.object2String(newValue).equals("")
                    //如果两个线程同时调用这个方法，当同时走到①的时候，
                    // 无论怎么样都有一个线程会先执行②这一行，
                    //假设线程1先执行②这行代码，那redis中key对应的value就变成了value
                    //然后线程2再执行②这行代码的时候，获取到的old_value就是value，
                    //那么value显然和他上面获取的current_value是不一样的，则线程2是没法获取锁的
                    && newValue.equals(current_value)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 释放锁
     * @param key id
     * @param value 时间戳
     */
    public void unlock(String key, String value) {
        try {
            if (StringUtils.object2String(redisTemplate.opsForValue().get(key)).equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
