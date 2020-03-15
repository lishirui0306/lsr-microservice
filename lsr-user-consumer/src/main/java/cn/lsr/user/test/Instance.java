package cn.lsr.user.test;

import cn.lsr.user.controller.user.UserController;

/**
 * @Description: 并发单例
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
public class Instance {
    private static volatile Instance instance;
    public Instance(){}
    public static Instance getInstance(){
        if (instance==null){
            synchronized (Instance.class){ //  1
                if (instance==null){//  2
                    instance = new Instance();//  3
                }
            }
        }
        return instance;
    }
}