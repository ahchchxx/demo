package com.example.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedissonTest {

    @Autowired
    RedissonClient redissonClient;

    @Test
    public void test() {
//        final int num = 10;
        List<String> list = new ArrayList<>();
//        CountDownLatch countDownLatch = new CountDownLatch(1);

        IntStream.range(0, 20).parallel().forEach(i -> {
            RLock lock = redissonClient.getLock("product001");
            lock.lock();
            try {
                if(list.size() >= 10) {
                    // list.add("finished");
                    System.out.println("failed: " + i);
                    return;
                }
                System.out.println("runing: " + i);
                Thread.sleep(1000);
                // num--;
                list.add(i + "");
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        });

        System.out.println("finished: ");
        list.forEach(s -> System.out.print(s + ", "));
        System.out.println("\ncount: "+ list.size());
    }
}
