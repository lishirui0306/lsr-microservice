package cn.lsr.core.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 自定义map缓存
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
public class CacheMap<K,V> {
    /**
     * 缓存存放map
     */
    final Map<K,V> map = new  HashMap<>();
    /**
     * 可重入读写锁
     */
    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    /**
     * 读锁
     */
    final Lock read = rwl.readLock();
    /**
     * 写锁
     */
    final Lock write = rwl.writeLock();

    V get(K key){
        V v = null;
        //先读缓存
        read.lock();
        try {
            v = map.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放读锁
            read.unlock();
        }
        //如果缓存存在就返回。
        if (null!=v){
            return v;
        }
        //如果缓存不存在，就去查数据库
        //获取写锁
        write.lock();
        try {
            v = map.get(key);
            //双重复验证，是为了避免并发，多线程，之前的线程已经执行过写操作了，重复执行。
            if (null!=v){
                // 查询数据库.......
                map.put(key,v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放写锁
            write.unlock();
        }
        return v;
    }
}
