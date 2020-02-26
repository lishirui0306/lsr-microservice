package cn.lsr.core.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description: 轮询线程基类
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
public abstract class AbstractPollThread {
    private static final Logger log = LoggerFactory.getLogger(AbstractPollThread.class);
    private long delay = 60; // 延期时间(第一次执行时间-调用时间)
    private long timeInterval = 60; //时间间隔(s)
    private Timer timer;
    private Timestamp lastProcess = null; //上次执行的时间
    private boolean isTimerStart = false; //定时器是否启动
    private boolean isOnProcessing = false; //是否在执行处理
    private final String threadName; //线程名称
    private final String threadId; //线程标识
    private final String longname; //中文名称
    private long processCount = 0; //执行次数
    private long processTime = 0; //已运行时间
    private long errorProcessCount = 0; //执行失败的次数

    public AbstractPollThread(String threadId, String threadName, String longname, Long delay, Long timeInterval) {
        this.threadId = threadId;
        this.threadName = threadName;
        this.longname = longname;
        if (timeInterval != null && timeInterval > 0)
            this.timeInterval = timeInterval;

        if (delay != null)
            this.delay = delay;
        PollThreadManager.get().register(threadName, this);
    }
    /**
     * 线程启动时回调方法。
     */
    public void init() {

    }

    /**
     * 轮询间隔回调方法。
     */
    public abstract void process();

    /**
     * 启动方法
     */
    public void startup() {
        this.timer = new Timer( "lsr-timer-" + this.threadName, false);
        try {
            this.timer.schedule(new TimerTask() {
                boolean initialized = false;
                @Override
                public void run() {
                    // 异步线程资源清理
                    //清理完上下文数据，在打log4j标记
                    log.info("\n--------------------轮训线程[" + AbstractPollThread.this.threadName + "]开始调度--------------------\n");
                    // 2015.4.20 每个线程开始受理请求时对数据库连接进行检查，若不可用则重连一次
                    long start = System.currentTimeMillis();
                    try {
                        AbstractPollThread.this.isOnProcessing = true;
                        AbstractPollThread.this.lastProcess = new Timestamp(System.currentTimeMillis());
                        AbstractPollThread.this.process();
                        AbstractPollThread.this.isOnProcessing = false;
                    }
                    catch (Throwable t) {
                         log.error("轮询线程出现异常", t);
                        AbstractPollThread.this.errorProcessCount++;
                    } finally {
                        AbstractPollThread.this.processCount++;
                        AbstractPollThread.this.processTime = AbstractPollThread.this.processTime + System.currentTimeMillis() - start;
						log.info("\n--------------------轮训线程[" + AbstractPollThread.this.threadName + "]调度结束. [" + (System.currentTimeMillis() - start) + "]--------------------\n");
                        //  异步线程资源清理
                    }
                }
            }, this.delay * 1000, this.timeInterval * 1000);
            this.isTimerStart = true;
        } catch (Exception e) {
            this.isTimerStart = false;
            log.error("轮询线程设置定时器失败,", e);
            throw new RuntimeException("轮询线程设置定时器失败", e);
        }
    }

    public void shutdown() {
        try {
            if (this.timer != null)
                this.timer.cancel();
            this.isTimerStart = false;
        } catch (Exception e) {
            this.isTimerStart = false;
            log.error("关闭轮询线程中的定时器失败", e);
            throw new RuntimeException("关闭轮询线程中的定时器失败", e);
        }
    }

    public String getThreadName() {
        return this.threadName;
    }

    public String getLongname() {
        return this.longname;
    }

    public long getProcessCount() {
        return this.processCount;
    }

    public long getProcessTime() {
        return this.processTime;
    }

    public long getErrorProcessCount() {
        return this.errorProcessCount;
    }

    public void setTimeInterval(long timeInterval) {
        this.timeInterval = timeInterval;
    }

    public long getTimeInterval() {
        return this.timeInterval;
    }

    public boolean isTimerStart() {
        return this.isTimerStart;
    }

    public boolean isOnProcessing() {
        return this.isOnProcessing;
    }

    public String getLastProcessTime() {
        return this.lastProcess == null ? null : this.lastProcess.toString();
    }

    public void resetCountInfo() {
        this.processCount = 0;
        this.processTime = 0;
        this.errorProcessCount = 0;
        this.lastProcess = null;
    }

}
