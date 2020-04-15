package cn.lsr.quartz.manager;

import cn.lsr.quartz.factory.QuartzJobFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @Description: 分布式任务定时管理配置
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
@Component
public class QuartzConfigManager {
    /**
     * 默认数据源--若不使用，使用的是配置文件的数据源
     */
    @Autowired
    DataSource dataSource;
    // 配置文件路径
    static final String QUARTZ_CONFIG = "/quartz.properties";
    /**
     * 工厂
     * @param myJobFactory
     * @return
     * @throws Exception
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(QuartzJobFactory myJobFactory) throws Exception {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //如果使用自定义的数据源就可以配置，如果是使用quartz扩展的不需要设置。
        //schedulerFactoryBean.setDataSource(dataSource);
        //使job实例支持spring 容器管理
        //用于Quartz集群,启动时更新已存在的Job
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setJobFactory(myJobFactory);
        schedulerFactoryBean.setConfigLocation(new ClassPathResource(QUARTZ_CONFIG));
        //可以手动加载quartz配置
        //schedulerFactoryBean.setQuartzProperties(PropertiesUtils.readProperties(QUARTZ_CONFIG));
        // 集群需要通过QuartzJobBean注入，需要设置上下文
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
        // 延迟10s启动quartz
        schedulerFactoryBean.setStartupDelay(10);
        return schedulerFactoryBean;
    }
    /**
     * 调度器
     * @param schedulerFactoryBean
     * @return
     * @throws IOException
     * @throws SchedulerException
     */
    @Bean
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) throws IOException, SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.start();
        return scheduler;
    }

}
