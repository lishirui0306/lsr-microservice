package cn.lsr.controller;

import cn.lsr.serivce.FlowJobService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: CVS文件入库
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
@RestController
public class CvsToDvController {
    @Autowired
    private FlowJobService flowJobService;
    // JobLauncher 由框架提供
    @Autowired
    JobLauncher jobLauncher;

    // Job 为刚刚配置的
    @Autowired
    Job job;
    @GetMapping("/hello")
    public void hello() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .toJobParameters();
            // 通过调用 JobLauncher 中的 run 方法启动一个批处理
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/run")
    public void run() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        flowJobService.run();
    }
}
