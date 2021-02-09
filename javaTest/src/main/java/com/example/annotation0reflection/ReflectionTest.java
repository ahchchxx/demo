package com.example.annotation0reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ReflectionTest {
    public static void main(String[] args) throws Exception {
        // get classes
        Class<ReflectionTest> cls = ReflectionTest.class;
        Class<?>[] classes = cls.getClasses();
        // get annotation for class
        Annotation[] declaredAnnotations = cls.getDeclaredAnnotations();

        // 1
        getFields(cls);
        getAllFields(cls); // get all fields in current class and all its father's fields

        // 2
        getMethods(cls);

        // 3 get class's interface
        getInterfaces(cls);
    }


    private static void getFields(Class<ReflectionTest> cls) throws Exception {
        // get fields, read & write data
        Field[] declaredFields = cls.getDeclaredFields();
        Field f = declaredFields[0];
        f.setAccessible(true);
        f.set(cls, "field value");

        // get field by name
        Field f1 = cls.getDeclaredField("a");

        // get annotation for Field
        boolean annotationPresent = f.isAnnotationPresent(Deprecated.class);
        Annotation[] declaredAnnotations = f.getDeclaredAnnotations();
    }
    private static void getAllFields(Class<? extends Object> cls) throws Exception {
        // Object.class.getSuperclass() is null
        while(cls.getSuperclass() != null && !cls.getSimpleName().equals("Object") ) {
            for (Field f : cls.getDeclaredFields()) {
                System.out.println(f.getName());
            }

            cls = cls.getSuperclass();
        }
    }

    private static void getMethods(Class<ReflectionTest> cls) throws NoSuchMethodException {
        // get methods
        Method[] methods = cls.getDeclaredMethods();

        // get params in method
        Method method = methods[0];
        Parameter[] parameters = method.getParameters();
        Class<?>[] parameterTypes = method.getParameterTypes();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();

        // get method by string of method's name
        Method method1 = cls.getDeclaredMethod("aMethodName", null);
        Method method2 = cls.getMethod("aMethodName", null);

        // get annotation for method
        Annotation[] annotations = method.getDeclaredAnnotations();
        boolean annotationPresent = method.isAnnotationPresent(Override.class);
        Deprecated annotation = method.getAnnotation(Deprecated.class);
        // String since = annotation.since();
        // annotation.xxx
    }

    private static void getInterfaces(Class<ReflectionTest> cls) {
        Class<?>[] interfaces = cls.getInterfaces();
    }

}
