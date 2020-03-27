package cn.lsr.core.config.bean;

import cn.lsr.core.ftp.config.FtpConfig;
import cn.lsr.core.thread.PollThreadConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: bean配置
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
@Configuration
public class BeanConfigManager {
    @Bean
    @ConfigurationProperties(prefix="lsr.poll.thread", ignoreUnknownFields = true)
    @ConditionalOnProperty(prefix="lsr.poll.thread", name="enabled", havingValue="true")
    public PollThreadConfig initPollThreadConfig(){
        return new PollThreadConfig();
    }
    @Bean
    @ConfigurationProperties(prefix = "lsr.ftp",ignoreUnknownFields = true)
    @ConditionalOnProperty(prefix = "lsr.ftp",name = "enabled",havingValue = "true")
    public FtpConfig initFtpConfig(){
        return new FtpConfig();
    }
}
