package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

// @Component
@EnableConfigurationProperties(RedisConfig.class)
public class JedisPoolUtil {
    @Autowired
    RedisConfig redisConfig;

//    JedisPool pool = null;

    @Bean
    // @ConditionalOnMissingBean
    public JedisPool jedisPoolUtil() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(redisConfig.getMaxNum());
        config.setMaxIdle(redisConfig.getMaxIdle());
        config.setMinIdle(redisConfig.getMinIdle());
        JedisPool pool = new JedisPool(config, redisConfig.getHost(), redisConfig.getPort());
        // pool = new JedisPool(config, redisConfig.getHost(), redisConfig.getPort());

        return pool;
    }

    @Autowired
    JedisPool pool;

    public Jedis getJedis() {
        return this.pool.getResource();
        //return pool.getResource();
    }

    public void release(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }

//    static
/*    @Autowired
    static
    RedisConfig redisConfig;

    private static JedisPool pool = null;
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(redisConfig.getMaxNum());
        config.setMaxIdle(redisConfig.getMaxIdle());
        config.setMinIdle(redisConfig.getMinIdle());
        pool = new JedisPool(config, redisConfig.getHost(), redisConfig.getPort());
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static void release(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }*/
}
