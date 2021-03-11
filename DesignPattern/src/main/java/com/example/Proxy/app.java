package com.example.Proxy;

/**
 * Proxy example
 * 静态代理，仅代理了一个类
 */
public class app {
    public static void main(String[] args) {
        Image img = new ProxyImage("abc.jpg");
        img.display();
    }
}
