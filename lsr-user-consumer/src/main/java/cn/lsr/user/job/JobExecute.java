package cn.lsr.user.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description: job执行入口
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
@Component
@DisallowConcurrentExecution
public class JobExecute implements Job {
    private Logger logger = LoggerFactory.getLogger(JobExecute.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取参数
        String s = (String) jobExecutionContext.getJobDetail().getJobDataMap().get("data");
        logger.info("开始执行任务===================，参数:{}",s);
    }
}
