package cn.lsr.core.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 轮询线程基类
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
public abstract class ScheduledThreadPoolBase {
    private static final Logger log = LoggerFactory.getLogger(ScheduledThreadPoolBase.class);
    /**
     * 线程数
     */
    private Integer size = 1;
    /**
     * 延迟时间
     */
    private Integer delayTime = 5;

    public ScheduledThreadPoolBase(Integer size,Integer delayTime) {
        this.size=size;
        this.delayTime=delayTime;
    }

    /**
     * 执行逻辑
     */
    public abstract void process();
    public void  start(){
        //ScheduledExecutorService scheduledExecutorService = Executors.
    }
}
