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
    /**
     * 查询所有的job
     * @return
     */
    List<JobInfo> list();

    /**
     * 新增job
     * @param info
     */
    void addJob(JobInfo info);

    /**
     * 修改job
     * @param info
     */
    void edit(JobInfo info);

    /**
     * 删除job
     * @param jobName
     * @param jobGroup
     */
    void delete(String jobName, String jobGroup);

    /**
     * 暂停job
     * @param jobName
     * @param jobGroup
     */
    void pause(String jobName, String jobGroup);

    /**
     * 重启job
     * @param jobName
     * @param jobGroup
     */
    void resume(String jobName, String jobGroup);

    /**
     * 检查job
     * @param jobName
     * @param jobGroup
     * @return
     * @throws SchedulerException
     */
    boolean checkExists(String jobName, String jobGroup)throws SchedulerException;
}
