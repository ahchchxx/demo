package com.example.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * A 线程，从 1 输出到 10
 * B 线程，当 A线程执行到 5时，给出提示，并结束
 */
public class test2 {

    public static void main(String[] args) {
        // fun1();
        // fun2();
        // fun3();
        // fun4();
        // fun5();
    }

    // example1：B 线程自旋判断状态
    volatile static int i = 1;// 使用 volatile 关键字，保证变量在不同线程中的“可见性”
    private static void fun1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start");
                for (i = 1; i <= 10; i++) {
                    System.out.println(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " end");
            }
        }, "Thread-A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start");
                while (true) { // this works, but effects the efficiency of cpu
                    if (i == 5) break;
                }
                System.out.println(Thread.currentThread().getName() + " end");
            }
        }, "Thread-B").start();
    }

    // example2: 使用 AtomicInteger
    private static void fun2() {
        AtomicInteger integer = new AtomicInteger();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start");
                for (int i = 1; i <= 10; i++) {
                    // integer.addAndGet(1);
                    // System.out.println(i);
                    // System.out.println(integer.get());
                    System.out.println(integer.addAndGet(1));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " end");
            }
        }, "Thread-A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start");
                while (true) { // this works, but effects the efficiency of cpu
                    if (integer.get() == 5) break;
                }
                System.out.println(Thread.currentThread().getName() + " end");
            }
        }, "Thread-B").start();
    }

    // example3: 使用 Thread 间通信的方法
    volatile static int count = 1;
    private static void fun3() {
        Object obj = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println(Thread.currentThread().getName() + " start");
                    if (count < 5) {
                        try {
                            obj.wait(); // Thread-B 暂停，并释放锁
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(Thread.currentThread().getName() + " end");
                    obj.notifyAll();
                }
            }
        }, "Thread-B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println(Thread.currentThread().getName() + " start");
                    for (count = 1; count <= 10; count++) {
                        System.out.println(count);
                        if (count == 5) {
                            obj.notifyAll(); // Thread-A 唤醒所有对 obj对象加 monitor的线程
                            try {
                                obj.wait(); // Thread-A 暂停，并释放锁，Thread-B才能执行
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " end");
                }
            }
        }, "Thread-A").start();
    }

    // example4: 使用 CountDownLatch，类似“信号枪”
    private static void fun4() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start");
                if (count < 5) {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread().getName() + " end");
            }
        }, "Thread-B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start");
                for (count = 1; count <= 10; count++) {
                    System.out.println(count);
                    if (count == 5)
                        countDownLatch.countDown();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " end");
            }
        }, "Thread-A").start();
    }

    // example5: 使用 LockSupport
    static Thread ta = null, tb = null;
    private static void fun5() {
        ta = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start");
                for (count = 1; count <= 10; count++) {
                    System.out.println(count);
                    if (count == 5) {
                        LockSupport.unpark(tb);
                        // LockSupport.park(); // useless?
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " end");
            }
        }, "Thread-A");
        tb = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start");
                if (count < 5) {
                    LockSupport.park();
                }

                System.out.println(Thread.currentThread().getName() + " end");
                LockSupport.unpark(ta);
            }
        }, "Thread-B");

        tb.start();
        ta.start();
    }

}
