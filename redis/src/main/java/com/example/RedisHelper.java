package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
@EnableConfigurationProperties(RedisConfig.class)
public class RedisHelper {
    @Autowired
    RedisConfig redisConfig;

    public Jedis getClient() {
         //return new Jedis(this.host, this.port);
        return new Jedis(redisConfig.getHost(), redisConfig.getPort());

//         use pool
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxIdle(8);
//        config.setMaxTotal(18);
//        JedisPool pool = new JedisPool(config, "127.0.0.1", 6379, 2000, "password");
//        return pool.getResource();
    }
    public String set(String key, String value) {
        Jedis r = getClient();

        return r.set(key, value);
    }
//    public String set(String key, String value, int expireSecond) {
//        Jedis r = getClient();
//        String ret = r.set(key, value);
//        r.expire(key, expireSecond);
//
//        return ret;
//    }
    public Long setNx(String key, String value) { // , int expireSecond
        Jedis r = getClient();
//        String ret = r.set(key, value, new SetParams());
//        r.expire(key, expireSecond);
        Long ret = r.setnx(key, value);
        return ret;
    }

    public String get(String key) {
        Jedis r = getClient();

        return r.exists(key) ? r.get(key) : "not exsit";
    }

    public Long del(String key) {
        Jedis client = getClient();

        return client.del(key);
    }
}
