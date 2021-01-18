package com.example.threads;

import java.util.concurrent.*;

public class ExecutorServiceSample {

    public static void main(String[] args) throws Exception {
        // fun1();
        fun2();
    }

    public static void fun1(){
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(10);// 批量创建 10个，线程几乎同时执行
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    countDownLatch.await();
                    System.out.println("Thread:" + Thread.currentThread().getName() + ", \ttime:" + System.currentTimeMillis());
                    // doSomeThing
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.countDown();
    }

    public static void  fun2() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<Double> callable = () -> {   // new Callable<>() { xxx }
            System.out.println(Thread.currentThread().getName());
            return Math.random();
        };

        for (int i = 0; i < 5; i++) {
            Future<Double> f1 = executorService.submit(callable);
            System.out.println(f1.get());// get result from Future
        }

        executorService.shutdown();
    }
}
