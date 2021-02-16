package com.example.BitMap;

import java.util.Arrays;
import java.util.BitSet;

/**
 * java 模拟 BitMap，对数组数据去重。
 * 核心：
 *  1，根据需要判重的数据的大小，来定义flags数组的长度
 *  2，setFlags() 和 getFlags()，操作flags数组，截取前 7位数字做下标，数组的值用。。？
 *
 *  ▲ 备记： 没有完全弄懂~~
 */
public class app {
    //public static final int _1MB = 1024 * 1024;
    //public static byte[] flags = new byte[ 512 * _1MB ];
    public static byte[] flags;

    public static void main(String[] args) {
        int[] array = {255, 1024, 1024, 0, 1, 2, 3, 65536, 0, 1024, 8888, 9999, 1111, 8888};

        int length = 65536 + 1;
        flags = new byte[(int) (length >> 3) // 右移 3 位，相当于：除以 8
                            + ((length & 7) > 0 ? 1 : 0) // 按位与 7，相当于：%8 除8取余（取模8）
                ];

        int index = 0;
        for (int num : array) {
            if (getFlags(num) == 1) {// 重复的元素
                continue;
            }
            //未出现的元素
            array[index] = num;
            index++;
            setFlags(num);//设置标志位
        }
        array = Arrays.copyOf(array, index);

        System.out.println(Arrays.toString(array));
        System.out.println(array.length);
    }

    public static void setFlags(int num) {
        int offset = num & (0x07);
        flags[num >> 3] |= 0x01 << offset;
    }
    public static int getFlags(int num) {
        int offset = num & (0x07);
        return flags[num >> 3] >> offset & 0x01;
    }


    /**
     * 使用java自带的BitSet，进行判重
     */
    private static void bitMapTest() {
        int [] array = new int [] {1,2,3,22,0,3,63};
        BitSet bitSet  = new BitSet(1); // long 类型的数组
        System.out.println(bitSet.size());   //64
        bitSet  = new BitSet(65);
        System.out.println(bitSet.size());   //128
        bitSet  = new BitSet(23);
        System.out.println(bitSet.size());   //64

        // 将数组内容组bitmap
        for(int i=0;i<array.length;i++)
        {
            bitSet.set(array[i], true);
        }

        System.out.println(bitSet.get(22));
        System.out.println(bitSet.get(60));

        System.out.println("下面开始遍历BitSet：");
        for ( int i = 0; i < bitSet.size(); i++ ){
            System.out.println(i + ", " + bitSet.get(i));
        }
    }
}