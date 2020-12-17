package com.example.integer;

public class IntegerTest {

    public static void main(String[] args) {
        Integer num3 = 128;
        Integer num4 = 128;
        System.out.println(num3 == num4);//打印 false

        num3 = 127;
        num4 = 127;
        System.out.println(num3 == num4);//打印 true
    }

}
