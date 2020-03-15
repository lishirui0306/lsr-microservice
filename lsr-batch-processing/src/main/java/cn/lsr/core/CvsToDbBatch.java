package cn.lsr.core;

import cn.lsr.entity.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

/**
 * @Description: CVS入库
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
public class CvsToDbBatch {
    // 注入JobBuilderFactory，用来构建Job
    @Autowired
    JobBuilderFactory jobBuilderFactory;

    // 注入StepBuilderFactory，用来构建Step
    @Autowired
    StepBuilderFactory stepBuilderFactory;

    // 注入DataSource，用来支持持久化操作，这里持久化方案是Spring-Jdbc
    @Autowired
    DataSource dataSource;

    // 配置一个ItemReader，即数据的读取逻辑
    @Bean
    @StepScope
    public FlatFileItemReader<User> itemReader() {
        // FlatFileItemReader 是一个加载普通文件的 ItemReader
        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
        // 由于data.csv文件第一行是标题，因此通过setLinesToSkip方法设置跳过一行
        reader.setLinesToSkip(1);
        // setResource方法配置data.csv文件的位置
        reader.setResource(new ClassPathResource("data.csv"));
        // 通过setLineMapper方法设置每一行的数据信息
        reader.setLineMapper(new DefaultLineMapper<User>(){{
            setLineTokenizer(new DelimitedLineTokenizer(){{
                // setNames方法配置了csv文件
                setNames("username", "password","usernick","imgurl","mail","locked");
                // 配置列与列之间的间隔符（这里是空格）
                setDelimiter(" ");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper(){{
                setTargetType(User.class);
            }});
        }});
        return reader;
    }
    // 配置ItemWriter，即数据的写出逻辑
    @Bean
    public JdbcBatchItemWriter jdbcBatchItemWriter() {
        // 使用的JdbcBatchltemWriter则是通过JDBC将数据写出到一个关系型数据库中。
        JdbcBatchItemWriter writer = new JdbcBatchItemWriter();
        // 配置使用的数据源
        writer.setDataSource(dataSource);
        // 配置数据插入SQL，注意占位符的写法是":属性名"
        writer.setSql("insert into user(id,username,sex) " +
                "values(:id,:username,:sex)");
        // 最后通过BeanPropertyItemSqlParameterSourceProvider实例将实体类的属性和SQL中的占位符一一映射
        writer.setItemSqlParameterSourceProvider(
                new BeanPropertyItemSqlParameterSourceProvider<>());
        return writer;
    }
    // 配置一个Step
    @Bean
    public Step csvStep() {
        // Step通过stepBuilderFactory进行配置
        return stepBuilderFactory.get("csvStep") //通过get获取一个StepBuilder，参数数Step的name
                .<User, User>chunk(2) //方法的参数2，表示每读取到两条数据就执行一次write操作
                .reader(itemReader()) // 配置reader
                .processor(itemProcessor()) // 配置processor
                .writer(jdbcBatchItemWriter()) // 配置writer
                .build();
    }
    // 配置ItemProcessor，进行数据处理    自定义
    @Bean
    public ItemProcessor<User,User> itemProcessor() {
        CvsItemProcessor cvsItemProcessor = new CvsItemProcessor();
        return cvsItemProcessor;
    }

    // 配置一个Job
    @Bean
    public Job csvJob() {
        // 通过jobBuilderFactory构建一个Job，get方法参数为Job的name
        return jobBuilderFactory.get("csvJob")
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {

                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {

                    }
                })
                .start(csvStep()) // 配置该Job的Step
                .build();
    }
}
