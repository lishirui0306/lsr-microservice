package cn.lsr.flow;

import cn.lsr.entity.ConsumeRecord;
import cn.lsr.entity.MonthBill;
import cn.lsr.entity.UserAccount;
import cn.lsr.excepiton.MoneyException;
import cn.lsr.repository.ConsumeRecordRepository;
import cn.lsr.repository.MonthBillRepository;
import cn.lsr.repository.UserAccountRepository;
import cn.lsr.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Description: 信用卡账单批处理
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
@Configuration
public class FlowBatchConfig {
    private static final Logger log = LoggerFactory.getLogger(FlowBatchConfig.class);

    private EntityManagerFactory entityManagerFactory;

    private StepBuilderFactory stepBuilderFactory;

    private JobBuilderFactory jobBuilderFactory;

    public FlowBatchConfig(EntityManagerFactory entityManagerFactory,StepBuilderFactory stepBuilderFactory,JobBuilderFactory jobBuilderFactory){
        this.entityManagerFactory=entityManagerFactory;
        this.stepBuilderFactory=stepBuilderFactory;
        this.jobBuilderFactory=jobBuilderFactory;
    }

    /**
     * 生成信用卡账单
     * @return
     */
    @Bean
    public Step generateVisaBillStep(ConsumeRecordRepository consumeRecordRepository){
        return  stepBuilderFactory.get("generateBillStep")
                .<ConsumeRecord, MonthBill>chunk(10)
                .reader(new JpaPagingItemReader<ConsumeRecord>(){{
                    setQueryString("from ConsumeRecord");
                    setEntityManagerFactory(entityManagerFactory);
                }})
                .processor((ItemProcessor<ConsumeRecord,MonthBill>) data->{
                    if (data.getGenerateBill()){
                        // 已生成的不会生成月账单
                        return null;
                    }else {
                        MonthBill monthBill = new MonthBill();
                        //组装账单
                        monthBill.setUserId(data.getUserId());
                        monthBill.setPaid(false);
                        monthBill.setNotice(false);
                        //计算利息
                        monthBill.setTotalFee(data.getConsumption().multiply(BigDecimal.valueOf(1.5d)));
                        monthBill.setCreateTime(new Date());
                        //是否生成账单
                        data.setGenerateBill(true);
                        consumeRecordRepository.save(data);
                        return monthBill;
                    }
                })
                .writer(new JpaItemWriter<MonthBill>(){{
                    setEntityManagerFactory(entityManagerFactory);
                }})
                .build();
    }

    /**
     * 自动扣费的
     * @param monthBillRepository 月账单
     * @param userAccountRepository 账户余额
     * @return
     */
    @Bean
    public Step autoDeductionStep(MonthBillRepository monthBillRepository,UserAccountRepository userAccountRepository){
        return stepBuilderFactory.get("autoDeductionStep")
                .<MonthBill, UserAccount>chunk(10)
                .reader(new JpaPagingItemReader<MonthBill>(){{
                    setQueryString("from MonthBill");
                    setEntityManagerFactory(entityManagerFactory);
                }})
                .processor((ItemProcessor<MonthBill,UserAccount>) data->{
                    if (data.getPaid()||data.getNotice()){
                        // 如果通知||已还款
                        return null;
                    }
                    // 根据账单信息查找账户信息
                    Optional<UserAccount> optionalUserAccount = userAccountRepository.findById(data.getUserId());
                    if (optionalUserAccount.isPresent()){
                        UserAccount userAccount = optionalUserAccount.get();
                        //账户状态检查
                        if(userAccount.getAccountStatus()==true){
                            //余额
                            if (userAccount.getAccountBalance().compareTo(data.getTotalFee()) > -1){
                                userAccount.setAccountBalance(userAccount.getAccountBalance().subtract(data.getTotalFee()));
                                //已还款
                                data.setPaid(true);
                                //已通知
                                data.setNotice(true);
                            }else{
                                // 余额不足
                                throw new MoneyException();
                            }
                        }else{
                            //状态异常
                            //设置通知
                            data.setNotice(true);
                            System.out.println(String.format("Message sent to UserID %s ——> your water bill this month is %s￥",data.getUserId(),data.getTotalFee()));

                        }
                        monthBillRepository.save(data);
                        return userAccount;
                    }else {
                        //账户不存在
                        log.error(String.format("用户ID %s,的用户不存在",data.getUserId()));
                        return null;
                    }
                })
                .writer(new JpaItemWriter<UserAccount>(){{
                    setEntityManagerFactory(entityManagerFactory);
                }})
                .build();
    }
    /**
     * 余额不足，扣款失败通知
     * @return
     */
    @Bean
    public Step visaPaymentNoticeStep(MonthBillRepository monthBillRepository){
        return stepBuilderFactory.get("visaPaymentNoticeStep")
                .tasklet((s,c)->{
                    List<MonthBill> monthBills = monthBillRepository.seleMothBillNoPlayAll(DateUtils.getBeginDayOfMonth(), DateUtils.getEndDayOfMonth());
                    monthBills.forEach(mo->{
                        System.out.println(String.format("Message sent to UserID %s ——> your water bill this month is ￥%s，please pay for it",
                                mo.getUserId(), mo.getTotalFee()));
                    });
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    public static void main(String[] args) {
        System.out.println(DateUtils.getBeginDayOfMonth()+"@"+DateUtils.getEndDayOfMonth());
    }
    /**
     * 流程开始
     * @param generateVisaBillStep 生成月账单
     * @param autoDeductionStep 自动扣费
     * @param visaPaymentNoticeStep 账户余额不足
     * @return
     */
    @Bean
    public Job flowJob(Step generateVisaBillStep,Step autoDeductionStep,Step visaPaymentNoticeStep){
        return jobBuilderFactory.get("flowJob")
                .listener(new JobExecutionListener() {
                    private long time;
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        time = System.currentTimeMillis();
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        System.out.println(String.format("任务耗时：%sms", System.currentTimeMillis() - time));
                    }
                })
                .flow(generateVisaBillStep)
                .next(autoDeductionStep)
                .next((jobExecution,stepExecution)->{
                    if (stepExecution.getExitStatus().equals(ExitStatus.COMPLETED)&&stepExecution.getCommitCount()>0){
                        return new FlowExecutionStatus("NOTICE USER");
                    }else {
                        return new FlowExecutionStatus(stepExecution.getStatus().toString());
                    }
                })
                .on("COMPLETED").end()
                .on("NOTICE USER").to(visaPaymentNoticeStep)
                .end()
                .build();
    }

}
