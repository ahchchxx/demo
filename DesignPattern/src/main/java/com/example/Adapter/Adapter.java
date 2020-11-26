package com.example.Adapter;

public class Adapter extends Source implements Targetable {

	@Override
	public void method2() {
		System.out.println("this is Adapter's method");
	}

}
