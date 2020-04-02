package cn.lsr.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: job执行入口
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
@Component
public class JobExecute implements Job {
    private Logger logger = LoggerFactory.getLogger(JobExecute.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("执行任务===================");

    }
}
