package cn.lsr.quartz.service;

import cn.lsr.quartz.entity.JobInfo;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * @Description: job任务接口
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
public interface JobTaskService {
    List<JobInfo> list();

    void addJob(JobInfo info);

    void edit(JobInfo info);

    void delete(String jobName, String jobGroup);

    void pause(String jobName, String jobGroup);

    void resume(String jobName, String jobGroup);

    boolean checkExists(String jobName, String jobGroup)throws SchedulerException;
}
