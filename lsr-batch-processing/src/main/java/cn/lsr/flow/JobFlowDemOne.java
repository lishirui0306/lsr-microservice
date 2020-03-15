package cn.lsr.flow;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: flow多job顺序执行
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
//@Configuration
public class JobFlowDemOne {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Bean
    public Job JobFlow(){
        return jobBuilderFactory.get("JobFlow").start(stepOne()).next(stepTwo()).next(stepThree()).build();
    }

    /**
     * 使用java8新特性
     * @return
     */
    @Bean
    public Step stepOne(){
        return stepBuilderFactory.get("stepOne").tasklet((stepContribution,chunkContext)->{
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step stepTwo(){
        return stepBuilderFactory.get("stepTwo").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("stepTwo->开始执行。。。");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }
    @Bean
    public Step stepThree(){
        return stepBuilderFactory.get("stepThree").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("stepThree->开始执行。。。");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

}

