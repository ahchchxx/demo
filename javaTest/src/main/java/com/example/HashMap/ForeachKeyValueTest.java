package com.example.HashMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
* 5 ways to foreach HashMap obj
 */
public class ForeachKeyValueTest {

    public static void main(String[] args) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("a", "");
        hashMap.put(null, null);

        // 1
        for (Map.Entry<Object, Object> set : hashMap.entrySet()) {
            set.getKey(); //set.getValue();
        }

        // 2
        Iterator<Map.Entry<Object, Object>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Object, Object> next = iterator.next();
            // next.getKey(); // next.getValue();
        }

        // 3
        Iterator<Object> iterator1 = hashMap.keySet().iterator();
        while (iterator1.hasNext()){
            // Object key = iterator1.next();// get key
            // hashMap.get(key) // get value
        }

        // 4 lambda
        hashMap.forEach((key, value) -> {
            // key  // value
        });

        // 5
        hashMap.entrySet().stream().forEach((entry) -> {
            // entry.getKey();
            // entry.getValue();
        });

    }

}
