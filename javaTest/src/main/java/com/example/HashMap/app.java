package com.example.HashMap;

import java.util.HashMap;

/**
 * init a HashMap instance in one line.
 *      wrapped in 2 braces
 *      {{ put(key, val) }}
 */
public class app {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<String, String>() {{
            put("a", "123");
            put("b", "789");
        }};

        System.out.println(map);
    }
}
