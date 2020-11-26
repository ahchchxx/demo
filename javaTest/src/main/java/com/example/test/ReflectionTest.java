package com.example.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ReflectionTest {
    public static void main(String[] args) throws Exception {

//        get classes
        Class<ReflectionTest> cls = ReflectionTest.class;
        Class<?>[] classes = cls.getClasses();

//        get fields, read & write data
        Field[] declaredFields = cls.getDeclaredFields();
        Field f = declaredFields[0];
        f.setAccessible(true);
        f.set(classes, "field value");

//        get field by name
        Field f1 = cls.getDeclaredField("a");

//        get annotation
        Annotation[] declaredAnnotations = cls.getDeclaredAnnotations();

//        get methods
        Method[] methods = cls.getDeclaredMethods();

//        get params in method
        Method method = methods[0];
        Parameter[] parameters = method.getParameters();
        Class<?>[] parameterTypes = method.getParameterTypes();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();

//        get method by string of method's name
        Method method1 = cls.getDeclaredMethod("aMethodName", null);


//        get annotation for method
        Annotation[] annotations = method.getDeclaredAnnotations();
        boolean annotationPresent = method.isAnnotationPresent(Override.class);
        Override annotation = method.getAnnotation(Override.class);
        // annotation.xxx

    }
}
