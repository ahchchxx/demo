package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RestController
public class MiaoShaController {
    @Autowired
    RedisHelper redisHelper;

    private long totalNum = 10;
    private int timeout = 20*1000;
    private String product_key = "p001--212";

    @RequestMapping("/miaosha")
    public String miaosha() {
        //List<String> sussessUsers = new ArrayList<>();
        List<String> users = new ArrayList<>();

        // init users
        IntStream.range(0, 1000).parallel().forEach(b -> {
            users.add("user-"+b);
        });

        totalNum = 20;
        users.parallelStream().forEach(b -> {
            //sussessUsers.add(qiang(b));
            qiang(b);
        });

        return "end";
    }

    private String qiang(String user) {
        long startTime = System.currentTimeMillis();
        while ((startTime + timeout) >= System.currentTimeMillis()) {
            if (totalNum < 1)
                break;
            if (redisHelper.setNx(product_key, "1") > 0) {
                System.out.println("user got the key, " + user);
                try {
                    if (totalNum < 1)
                        break;
                    Thread.sleep(200);

                    // System.out.println("user get the good, " + user);
                    System.out.println("current num of good: " + totalNum);
                    totalNum--;

                    return "";
                } catch (Exception e) {

                } finally {
                    System.out.println("user release the lock," + user);
                    System.out.println("\n");
                    redisHelper.del(product_key);
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("user is trying, " + user + ", left: " + totalNum);
        }
        return "";
    }
}
