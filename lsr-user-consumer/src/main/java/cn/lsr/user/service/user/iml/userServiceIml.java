package cn.lsr.user.service.user.iml;

import cn.lsr.redis.core.RedisLock;
import cn.lsr.user.entity.user.User;
import cn.lsr.user.mapper.user.UserMapper;
import cn.lsr.user.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * = =
 *
 * @Version: 1.0
 * @Author: Hacker_lsr@126.com
 */
@Service
public class userServiceIml implements UserService {
    //@Autowired
    //private RedisLock redisLock;
    @Resource
    private UserMapper userMapper;
    @Override
    public User selectByName(String username) {
        String value = System.currentTimeMillis()+1000+"";
        //redis加锁
//        if (!redisLock.lock(username,value)){
//            throw new RuntimeException("加锁错误！");
//        }
//        if("判断剩余".equals("0")){
//            // "活动已经结束了"; 已经卖完了。
//            return null;
//        }else {//还没有卖完
//            try {
//                Thread.sleep(100);
//                //模拟数据库操作 商品 -1
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        //释放锁
//        redisLock.unlock(username,value);
        return userMapper.selectByName(username);
    }

    @Override
    public User selectUserByName(String username) {
        return userMapper.selectUserByName(username);
    }

    @Override
    public User selecTest(String username) {
        return userMapper.selecTest(username);
    }
}
