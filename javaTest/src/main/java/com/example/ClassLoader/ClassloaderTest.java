package com.example.ClassLoader;

import java.net.URL;

public class ClassloaderTest {

    public static void main(String[] args) throws ClassNotFoundException {
        // 0, get location
        URL url = ClassloaderTest.class.getResource("/");
        System.out.println(url.getPath()); //.toString()
        // System.out.println(System.getProperty("java.class.path"));
        System.out.println(System.getProperty("java.class.path").split(";")[0]);


        // 1, Class.forName
        Class<?> aClass1 = Class.forName("com.example.ClassLoader.ClassForName");
        System.out.println(aClass1.getName());
        System.out.println("↑ ↑ ↑ ↑ ↑   Class.forName\n");

        // 2, ClassLoader.  it won't initialize class...
        Class<?> aClass2 = ClassLoader.getSystemClassLoader().loadClass("com.example.ClassLoader.ClassForName");
        System.out.println(aClass2.getName());
    }

}
