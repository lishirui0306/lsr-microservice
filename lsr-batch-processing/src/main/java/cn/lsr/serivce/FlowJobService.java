package cn.lsr.serivce;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description: 流程job
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
@Service
public class FlowJobService {
    private JobLauncher jobLauncher;
    private Job flowJob;
    @Autowired
    public FlowJobService(JobLauncher jobLauncher,Job flowJob){
        this.jobLauncher=jobLauncher;
        this.flowJob=flowJob;
    }
    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void run() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters jobParameters = new JobParametersBuilder().addDate("time", new Date()).toJobParameters();
        jobLauncher.run(flowJob, jobParameters);
    }
}
