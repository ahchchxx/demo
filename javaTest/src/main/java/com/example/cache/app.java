package com.example.cache;

public class app {

    public static void main(String[] args) {
        LRUCache<String, Integer> cache = new LRUCache<>(8);
        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        cache.printNodes(cache.getHead());

        cache.put("a", 8);
        cache.printNodes(cache.getHead());

        cache.put("d", 9);
        cache.put("e", 99);

        System.out.println("Current Content: ");
        cache.printNodes(cache.getHead());
    }

}
