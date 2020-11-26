package com.example.Adapter_1;

/**
 * 对象的适配器模式
 */
public class AppTest {

	public static void main(String[] args) {
		Source source = new Source();
		Targetable target = new Wrapper(source);
		target.method1();
		target.method2();
	}
	
}
