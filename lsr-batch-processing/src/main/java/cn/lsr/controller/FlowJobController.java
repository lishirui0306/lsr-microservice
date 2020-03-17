package cn.lsr.controller;

import cn.lsr.serivce.FlowJobService;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 流程控制器
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
@RestController
public class FlowJobController {
    @Autowired
    private FlowJobService flowJobService;

    @GetMapping("/run")
    public void run() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        flowJobService.run();
    }
}
