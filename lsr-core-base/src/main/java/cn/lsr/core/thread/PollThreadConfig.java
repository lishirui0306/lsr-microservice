package cn.lsr.core.thread;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 轮询线程配置
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
public class PollThreadConfig {
    /**
     * 轮询线程ID
     */
    private String threadId;

    /**
     * 轮询线程名称
     */
    private String threadName;

    /**
     * 间隔时间
     */
    private Long timeInterval;

    /**
     * 轮询线程首次延迟时间
     */
    private Long delay;

    public Long getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(Long timeInterval) {
        this.timeInterval = timeInterval;
    }

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

}
