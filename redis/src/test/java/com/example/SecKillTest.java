package com.example;

import com.example.redislock.CacheLockInterceptor;
import com.example.redislock.secondkill.SecKillImpl;
import com.example.redislock.secondkill.SeckillInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Proxy;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
public class SecKillTest {

    @Test
    public void testSecKill() {
        // int threadCount = 1000;
        // int splitPoint = 500;
        int threadCount = 100;
        int splitPoint = 50;
        CountDownLatch beginCount = new CountDownLatch(1);
        CountDownLatch endCount = new CountDownLatch(threadCount);
        SecKillImpl testClass = new SecKillImpl();

        Thread[] threads = new Thread[threadCount];
        //起500个线程，秒杀第一个商品
        for (int i = 0; i < splitPoint; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    try {
                        //等待在一个信号量上，挂起
                        beginCount.await();
                        //用动态代理的方式调用secKill方法
                        SeckillInterface proxy = (SeckillInterface) Proxy.newProxyInstance(SeckillInterface.class.getClassLoader(),
                                new Class[]{SeckillInterface.class}, new CacheLockInterceptor(testClass));
                        proxy.secKill("test", 10000001L);  // commidityId1
                        endCount.countDown();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
        }

        //再起500个线程，秒杀第二件商品
        for (int i = splitPoint; i < threadCount; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    try {
                        //等待在一个信号量上，挂起
                        beginCount.await();
                        //用动态代理的方式调用secKill方法
                        SeckillInterface proxy = (SeckillInterface) Proxy.newProxyInstance(SeckillInterface.class.getClassLoader(),
                                new Class[]{SeckillInterface.class}, new CacheLockInterceptor(testClass));
                        proxy.secKill("test", 10000002L);  // commidityId2
                        //testClass.testFunc("test", 10000001L);
                        endCount.countDown();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
        }

        long startTime = System.currentTimeMillis();
        //主线程释放开始信号量，并等待结束信号量，这样做保证1000个线程做到完全同时执行，保证测试的正确性
        beginCount.countDown();

        try {
            //主线程等待结束信号量
            endCount.await();
            //观察秒杀结果是否正确
            System.out.println(SecKillImpl.inventory.get(10000001L)); // commidityId1
            System.out.println(SecKillImpl.inventory.get(10000002L)); // commidityId2
            System.out.println("error count: " + CacheLockInterceptor.ERROR_COUNT);
            System.out.println("total cost: " + (System.currentTimeMillis() - startTime));

            System.in.read();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
