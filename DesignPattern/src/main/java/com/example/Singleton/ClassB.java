package com.example.Singleton;

/**
 * 饿汉模式
 */
public class ClassB {

	private ClassB() {}
	
	private static ClassB clsB = new ClassB();
	public static ClassB getInstance() {
		return clsB;
	}
	
}
