package com.example.objPool;

import java.util.stream.IntStream;

public class app {

    public static void main(String[] args) {
        // init pool
        SimplePool simplePool = new SimplePool(10);
        for (int i = 0; i < 10; i++) {
            simplePool.put("" + (i + 1));
        }

        // use obj in pool, and put it back
        IntStream.range(0, 50).parallel().forEach(i -> {
            Object o = simplePool.get();
            System.out.println(o);

            try {
                Thread.sleep(100 * i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            simplePool.put(o);
        });
    }

}
