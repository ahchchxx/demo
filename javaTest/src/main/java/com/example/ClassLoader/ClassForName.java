package com.example.ClassLoader;

public class ClassForName {
    static {
        System.out.println("static block");
    }
    public static String getStaticVal() {
        System.out.println("static method");
        return "abc";
    }

    public static String staticField = getStaticVal();

}
