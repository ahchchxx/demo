package com.example.annotation0reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class AnnotationTest {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface myAnno{
        String value();
    }

    @myAnno("a")
    public static void myFun(){
        System.out.println("hello");
    }

    public static void main(String[] args) throws Exception {
        myFun();

        Method m = AnnotationTest.class.getMethod("myFun", null); // String[].class
        myAnno anno = m.getAnnotation(myAnno.class);
        System.out.println(anno.value());

    }
}