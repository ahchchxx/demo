package com.example.test;

import com.example.App;
import com.example.JedisPoolUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

//@RunWith(App.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class JedisPollTest {

    @Autowired
    JedisPoolUtil jedisPoolUtil;

    @Test
    public void test1() {
        Jedis jedis = jedisPoolUtil.getJedis();
//        Jedis jedis = JedisPoolUtil.getJedis();
        String set = jedis.set("abc", "abc123");
        System.out.println(set);

        String abc = jedis.get("abc");
        System.out.println(abc);
    }

}
