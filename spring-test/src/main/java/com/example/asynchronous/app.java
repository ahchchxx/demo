package com.example.asynchronous;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Future;

/**
 * 异步学习
 * 抄的wx文章，不知道具体的作用
 */
@EnableAsync
@SpringBootApplication
public class app {

    public static void main(String[] args) {
        // SpringApplication.run(app.class, args);
        app a1 = new app();
        Future<String> stringFuture = a1.doReturn(1);// 在响应前卡顿住？
        try {
            String s = stringFuture.get();
            System.out.println(s);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Async
    public Future<String> doReturn(int i) {
        try {
            Thread.sleep(3000);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new AsyncResult<>("asyn return");
    }

}
