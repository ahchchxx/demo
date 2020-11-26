package com.example.produce0consume;

import java.util.ArrayDeque;
import java.util.Queue;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Queue<Product> queue = new ArrayDeque<>();

        for (int i = 0; i < 10; i++) {
            new Thread(new Producer(queue, 10)).start();
            new Thread(new Consumer(queue, 10)).start();
            Thread.sleep(200);
        }
    }
}
