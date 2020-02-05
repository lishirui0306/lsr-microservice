package cn.lsr.redis.controller;

import cn.lsr.redis.api.RedisInterFace;
import cn.lsr.redis.utils.RedisResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: redis锁服务controller
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 * @version: V1.0
 **/
@RestController
public class RedisController {

    @Autowired
    private RedisInterFace redisInterFace;
    @GetMapping("getRedisLock")
    public RedisResult getRedisLock(String id, String value){
        return redisInterFace.lock(id,value);
    }
    @GetMapping("unRedisLock")
    public RedisResult unRedisLock(String id,String value){
        return redisInterFace.unlock(id,value);
    }
}
