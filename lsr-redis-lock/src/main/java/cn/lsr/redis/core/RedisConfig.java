package cn.lsr.redis.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

import java.nio.charset.Charset;

/**
 * @Description: redis配置类
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 * @version: V1.0
 **/
@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.usePool}")
    private boolean usePool;
    @Value("${spring.redis.useSsl}")
    private boolean useSsl;

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory j = new JedisConnectionFactory();
        //绑定ip
        j.setHostName(host);
        //监听端口
        j.setPort(port);
        //客户端闲置多少时间后断开连接
        j.setTimeout(timeout);
        j.setUsePool(usePool);
        j.setUseSsl(useSsl);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        j.setPoolConfig(jedisPoolConfig);
        j.setConvertPipelineAndTxResults(true);
        return j;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer(Charset.forName("UTF8")));
        return redisTemplate;
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("106.54.114.230",6379);
        jedis.ping();
        System.out.println(jedis.ping());
    }

}
