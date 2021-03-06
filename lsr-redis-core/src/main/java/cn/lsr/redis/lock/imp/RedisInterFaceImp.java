package cn.lsr.redis.lock.imp;

import cn.lsr.redis.core.RedisLock;
import cn.lsr.redis.lock.RedisInterFace;
import cn.lsr.redis.utils.RedisResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: redis锁实现
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 * @version: V1.0
 **/
@Service
public class RedisInterFaceImp implements RedisInterFace {
    private static final Logger log = LoggerFactory.getLogger(RedisInterFaceImp.class);
    @Autowired
    private RedisLock redisLock;
    @Override
    public RedisResult lock(String id, String value) {
        if (!redisLock.lock(id,value)){
            log.info("获取redis失败 错误！！标识为："+id);
            return RedisResult.error(false,"获得redis锁错误！！！！ 标识为："+id);
        }
        log.info("获得redis锁成功 标识为："+id);
        return RedisResult.success(true,"获得redis锁成功 标识为："+id);
    }

    @Override
    public RedisResult unlock(String id, String value) {
        redisLock.unlock(id,value);
        log.info("释放redis锁成功 标识为："+id);
        return RedisResult.success(true,"释放redis锁成功 标识为："+id);
    }
}
