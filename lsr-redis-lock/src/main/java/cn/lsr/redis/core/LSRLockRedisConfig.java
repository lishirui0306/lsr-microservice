package cn.lsr.redis.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Description: 锁的配置
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 * @version: V1.0
 **/
@Configuration
public class LSRLockRedisConfig extends LSRBaseRedisConfig {
    private static final Logger log = LoggerFactory.getLogger(LSRLockRedisConfig.class);
    @Primary
    @Bean(name = "lsrLockJedisConnectionFactory")
    @Override
    public JedisConnectionFactory buildJedisConnectionFactory(@Qualifier("lsrLockRedisConfig")RedisPropertiesConfig redisPropertiesConfig) {
        log.info("MasterRedisConfig RedisPropertiesConfig:{}",redisPropertiesConfig);
        return super.buildJedisConnectionFactory(redisPropertiesConfig);
    }

    @Bean(name = "lsrLockRedisTemplate")
    @Override
    public RedisTemplate<Object, Object> buidRedisTemplate(@Qualifier("lsrLockJedisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
        return super.buidRedisTemplate(redisConnectionFactory);
    }

    @Bean(name = "lsrLockRedisConfig")
    @ConfigurationProperties(prefix = "spring.redis.lock")
    public RedisPropertiesConfig getBaseDBProperties() {
        return new RedisPropertiesConfig();
    }

}
