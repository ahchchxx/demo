package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/redis")
//@EnableCaching
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    RedisHelper redisHelper;

    @RequestMapping("/set")
    public String set(String key, String value) {
//        Jedis j = redisHelper.getClient();
//        return j.set(key, value);
        return redisHelper.set(key, value);
    }
    @RequestMapping("/get")
    public String get(String key) {
//        Jedis j = redisHelper.getClient();
//        return j.get(key);
        return redisHelper.get(key);
    }
}
