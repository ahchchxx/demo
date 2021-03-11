package com.example.Proxy;

/**
 * Proxy example
 */
public class app {
    public static void main(String[] args) {
        Image img = new ProxyImage("abc.jpg");
        img.display();
        img.display();
    }
}
