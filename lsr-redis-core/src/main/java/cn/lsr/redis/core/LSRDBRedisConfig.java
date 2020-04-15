package cn.lsr.redis.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
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

    /**
     *  初始化 jedis lsrDBRedisProperties 连接工厂 -- lsrDBJedisConnectionFactory
     * @param redisPropertiesConfig
     * @return
     */
    @Bean(name = "lsrDBJedisConnectionFactory")
    @Override
    public RedisConnectionFactory redisConnectionFactory(@Qualifier("lsrDBRedisProperties")RedisPropertiesConfig redisPropertiesConfig){
        log.info("lsrDBRedisConfig RedisPropertiesConfig:{}",redisPropertiesConfig);
        return super.redisConnectionFactory(redisPropertiesConfig);
    }

    /**
     * 开启缓存
     * @param redisConnectionFactory
     * @return
     */
    @Bean(name = "DbCache")
    @Override
    public CacheManager cacheManager(@Qualifier("lsrDBJedisConnectionFactory")RedisConnectionFactory redisConnectionFactory) {
        return super.cacheManager(redisConnectionFactory);
    }

    /**
     * 初始化工厂中 lsrDBJedisConnectionFactory  的 lsrDBRedisTemplate
     * @param redisConnectionFactory
     * @return
     */
    @Bean(name = "lsrDBRedisTemplate")
    @Override
    public RedisTemplate <Object, Object> buidRedisTemplate(@Qualifier("lsrDBJedisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
        return super.buidRedisTemplate(redisConnectionFactory);
    }

    /**
     * 启动加载配置文件 yml  redis 连接参数
     * @return
     */
    @Bean(name = "lsrDBRedisProperties")
    @ConfigurationProperties(prefix = "spring.redis.db")
    public RedisPropertiesConfig getBaseDBProperties() {
        return new RedisPropertiesConfig();
    }

}
