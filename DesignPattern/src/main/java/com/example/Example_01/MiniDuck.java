package com.example.Example_01;

public class MiniDuck extends Duck {
	public MiniDuck() {
		flyAble = new FlyWithWings();
		quackAble = new QuackGaga();
	}
	
}
