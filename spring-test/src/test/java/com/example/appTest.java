package com.example;

import com.example.asynchronous.AsyncConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 原生版 spring 测试方法
 */
public class appTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        AsyncConfig bean = ac.getBean(AsyncConfig.class);

        System.out.println(bean);
    }
}
