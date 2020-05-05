package cn.lsr.core.concurr;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * @Description: GuardedObject的增强 Guarded Suspension模式本质上是一种等待唤醒机制的实现
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
public class GuardedObject<T> {
    /**
     * 受保护的对象T
     */
    T obj;
    final Lock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();
    final int timeout = 2;
    /**
     * 保存所有GuardedObject
     */
    final static Map<Object,GuardedObject> gos = new ConcurrentHashMap<>();

    /**
     * 静态方法创建GuardedObject
     * @param key
     * @param <K>
     * @return
     */
    static <K> GuardedObject creat(K key){
        GuardedObject go = new GuardedObject();
        gos.put(key,go);
        return go;
    }
    static <K,T> void fireEvent(K key,T obj){
        GuardedObject go = gos.remove(key);
        if (null!=go){
            go.onChanged(obj);
        }
    }
    T get(Predicate<T> p){
        lock.lock();
        try {
            while (!p.test(obj)){
                condition.await(timeout, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
        return obj;
    }
    /**
     * 事件通知方法
     * @param obj
     */
    void onChanged(T obj) {
        lock.lock();
        try {
            this.obj = obj;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
