package cn.lsr.redis.core;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @Description: redis配置基类
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 * @version: V1.0
 **/
public class LSRBaseRedisConfig {
    /**
     * jedis 连接工厂
     * @param redisPropertiesConfig
     * @return
     */
    public JedisConnectionFactory buildJedisConnectionFactory(RedisPropertiesConfig redisPropertiesConfig) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setDatabase(redisPropertiesConfig.getDatabase());
        jedisConnectionFactory.setHostName(redisPropertiesConfig.getHost());
        jedisConnectionFactory.setPort(redisPropertiesConfig.getPort());
        jedisConnectionFactory.setPassword(redisPropertiesConfig.getPassword());
        jedisConnectionFactory.setTimeout(redisPropertiesConfig.getTimeout());

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisPropertiesConfig.getPool().getMaxIdle());
        poolConfig.setMinIdle(redisPropertiesConfig.getPool().getMinIdle());
        poolConfig.setMaxTotal(redisPropertiesConfig.getPool().getMaxActive());
        poolConfig.setMaxWaitMillis(redisPropertiesConfig.getPool().getMaxWait());
        poolConfig.setTestOnBorrow(true);

        jedisConnectionFactory.setPoolConfig(poolConfig);
        return jedisConnectionFactory;

    }
    private Duration timeToLive = Duration.ZERO;
    public void setTimeToLive(Duration timeToLive) {
        this.timeToLive = timeToLive;
    }

    /**
     * 缓存
     * @param factory
     * @return
     */
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 配置序列化（解决乱码的问题）
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(timeToLive)
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .disableCachingNullValues();
        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                .build();
        return cacheManager;
    }
//
//        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
//        return redisCacheManager;
    //}

    /**
     * RedisTemplate 初始化 序列化和反序列化
     * @param redisConnectionFactory
     * @return
     */
    public RedisTemplate buidRedisTemplate(RedisConnectionFactory redisConnectionFactory) {

        /* Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        //template.setKeySerializer(jackson2JsonRedisSerializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
        */
        RedisSerializer stringSerializer = new StringRedisSerializer();
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        return redisTemplate;
    }
}
