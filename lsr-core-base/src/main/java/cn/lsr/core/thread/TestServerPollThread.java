package cn.lsr.core.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

/**
 * @Description: 测试轮询线程
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
public class TestServerPollThread extends AbstractPollThread{
    private static final Logger log = LoggerFactory.getLogger(TestServerPollThread.class);
    /**
     * 插件     - OnlineServerManagerPlugin -启动的时候初始化线程
     * @param threadId 轮询线程ID
     * @param threadName 轮询线程名称
     * @param longname  中文名称
     * @param delay 轮询线程首次延迟时间
     * @param timeInterval 时间间隔
     */
    public TestServerPollThread(String threadId, String threadName, String longname, Long delay, Long timeInterval) {
        super(threadId, threadName, longname, delay, timeInterval);
    }

    /**
     * 轮询间隔回调方法
     */
    @Override
    public void process() {
        log.info("刷新时间为：{}", new Timestamp(System.currentTimeMillis()));
       //逻辑
    }
}
