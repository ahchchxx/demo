package com.example.Type;

/**
 * 数据类型，及其所占体积大小
 */
public class app {
    public static void main(String[] args) {
        print("Short.MAX_VALUE", Short.MAX_VALUE); // 32767       2^15 -1
        print("Short.MIN_VALUE", Short.MIN_VALUE); // -32768      - 2^15

        print("Integer.MAX_VALUE", Integer.MAX_VALUE); // 2147483647    2^31 -1
        print("Integer.MIN_VALUE", Integer.MIN_VALUE); // -2147483648   - 2^31

        print("Byte: ", Byte.SIZE); // 8 bit     1byte
        print("Short: ", Short.SIZE); // 16
        print("Character: ", Character.SIZE); // 16
        print("Integer: ", Integer.SIZE); // 32
        print("Long: ", Long.SIZE); // 64
    }

    public static void print(String str, Object obj) {
        System.out.println(str + " : " + obj);
    }
}
