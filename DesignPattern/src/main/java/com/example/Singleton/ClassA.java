package com.example.Singleton;

/**
 * 懒汉模式
 */
public class ClassA {
	private	ClassA() {} // 设置构造 private，没有返回值
	
	// private static volatile ClassA clsA = null; // :)
	private static ClassA clsA = null;
	public static synchronized ClassA getInstance() {
		if (clsA == null) {
			clsA = new ClassA();
		}
		return clsA;
	}
	
}
