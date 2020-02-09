package cn.lsr.redis.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Description: 数据操作redis配置
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 * @version: V1.0
 **/
@Configuration
public class LSRDBRedisConfig extends LSRBaseRedisConfig {
    private static final Logger log = LoggerFactory.getLogger(LSRDBRedisConfig.class);
    @Bean(name = "lsrDBJedisConnectionFactory")
    @Override
    public JedisConnectionFactory buildJedisConnectionFactory(@Qualifier("lsrDBRedisProperties")RedisPropertiesConfig redisPropertiesConfig) {
        log.info("lsrDBRedisConfig RedisPropertiesConfig:{}",redisPropertiesConfig);
        return super.buildJedisConnectionFactory(redisPropertiesConfig);
    }

    @Bean(name = "lsrDBRedisTemplate")
    @Override
    public RedisTemplate <Object, Object> buidRedisTemplate(@Qualifier("lsrDBJedisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
        return super.buidRedisTemplate(redisConnectionFactory);
    }

//    @Bean
//    @Override
//    public CacheManager cacheManager(@Qualifier("lbsRedisTemplate")RedisTemplate redisTemplate) {
//        return super.cacheManager(redisTemplate);
//    }

    @Bean(name = "lsrDBRedisProperties")
    @ConfigurationProperties(prefix = "spring.redis.db")
    public RedisPropertiesConfig getBaseDBProperties() {
        return new RedisPropertiesConfig();
    }

}
