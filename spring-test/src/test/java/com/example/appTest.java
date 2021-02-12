package com.example;

import com.example.asynchronous.AsyncConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 原生版 spring 测试方法
 */
public class appTest {
    public static void main(String[] args) {
        // init way 1: by Class file
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // init way 2: by base package string
        // AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("com.example");

        // get bean
        AsyncConfig bean = ac.getBean(AsyncConfig.class);

        System.out.println(bean);
    }
}
