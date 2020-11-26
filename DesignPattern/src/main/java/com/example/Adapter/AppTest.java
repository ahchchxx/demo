package com.example.Adapter;

/**
 * 类的适配模式
 */
public class AppTest {

	public static void main(String[] args) {
		Targetable target = new Adapter();
		target.method1();
		target.method2();
	}
	
}
