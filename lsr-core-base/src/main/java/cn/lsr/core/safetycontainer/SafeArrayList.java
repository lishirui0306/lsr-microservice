package cn.lsr.core.safetycontainer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 线程安全的arraylist
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
public class SafeArrayList<T> {
    /**
     * 封装arraylist
     */
    List<T> list = new ArrayList<>();
    //控制访问路径
    synchronized
    T get(int index){
        return list.get(index);
    }
    synchronized
    void add(T t){
        list.add(t);
    }

    /**
     * 这个方法也是用 synchronized 来保证原子性 的。
     * @param t
     * @return
     */
    synchronized
    boolean addIfNotExist(T t){
        //是否包含
        if (!list.contains(t)){
            list.add(t);
            return true;
        }
        return false;
    }
}
