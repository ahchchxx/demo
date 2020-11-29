package com.example.thread;

import java.io.IOException;

public class test {

    public static void main(String[] args) throws IOException {
        Object lock = new Object();
        final Integer[] i = {0};
        int c = 97;

//        System.out.println((char)c);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (i[0] < 10) {
                    // System.out.println("a-" + i[0]);
                    synchronized (lock) {
                        lock.notifyAll();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.print((char) (c + i[0]));
                        i[0]++;
                        lock.notifyAll();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (i[0] < 10) {
                    synchronized (lock) {
                        lock.notifyAll();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.print(i[0] + " ");
                        lock.notifyAll();
                    }
                }
            }
        }).start();

        System.in.read();
    }

}
