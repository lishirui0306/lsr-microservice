package cn.lsr.core;

import cn.lsr.entity.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.support.DatabaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @Description: CVS文件处理
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
//@Configuration
public class CsvBatchConfig {
    private final DataSource dataSource;
    private final PlatformTransactionManager platformTransactionManager;
    @Autowired
    public CsvBatchConfig(@Qualifier("dataSource")DataSource dataSource , PlatformTransactionManager platformTransactionManager){
        this.dataSource=dataSource;
        this.platformTransactionManager=platformTransactionManager;
    }
    @Bean
    public JobRepository jobRepository ()throws Exception{
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setDatabaseType(DatabaseType.MYSQL.getProductName());
        jobRepositoryFactoryBean.setTransactionManager(platformTransactionManager);
        return jobRepositoryFactoryBean.getObject();
    }
    @Bean
    public JobLauncher jobLauncher(JobRepository jobRepository){
        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(jobRepository);
        return simpleJobLauncher;
    }
    @Bean
    public ItemReader<User> csvItemReader(){
        return new FlatFileItemReader<User>(){{
            setResource(new ClassPathResource("before.csv"));
            // 用于设置文件与对象的映射关系
            setLineMapper(new DefaultLineMapper<User>(){{
                setLineTokenizer(new DelimitedLineTokenizer() {{setNames("username", "password","usernick","imgurl","mail","locked"); }});
                setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{setTargetType(User.class);}});
                }
            });
        }};
    }
    @Bean
    public ItemWriter<User> csvItemWriter(){
        // 默认会清空文件重新编写，如需追加请指定append为true
        return new FlatFileItemWriterBuilder<User>()
                .append(false)
                .name("writer")
                .resource(new ClassPathResource("after.csv"))
                .lineAggregator(new DelimitedLineAggregator<User>(){{
                    setFieldExtractor(new BeanWrapperFieldExtractor<User>(){{setNames(new String[]{"username", "password","usernick","imgurl","mail","locked"}); }});
                }})
                .build();
    }
    @Bean
    public Step csvStep(JobRepository jobRepository,ItemReader<User> itemReader,ItemWriter<User> itemWriter){
        return new StepBuilderFactory(jobRepository,platformTransactionManager).get("csvStep")
                .<User,User>chunk(5)
                .reader(itemReader)
                //逻辑处理
                .processor((ItemProcessor<User,User>) batchUser -> {batchUser.setPassword(batchUser.getPassword()+100); return batchUser;})
                .writer(itemWriter)
                .build();
    }
    @Bean
    public Job csvJob(JobRepository jobRepository,Step step){
        return new JobBuilderFactory(jobRepository).get("csvJob").incrementer(new RunIdIncrementer()).start(step).build();
    }
}
