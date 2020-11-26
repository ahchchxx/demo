package com.example.Example_01;

public class Duck {
	IFlyAble flyAble;
	IQuackAble quackAble;
	
	public void PerformFly() {
		flyAble.Fly();
	}
	public void PerformQuack() {
		quackAble.Qucak();
	}
	
	public void SetFlyAble(IFlyAble flyAble) {
		this.flyAble = flyAble;
	}
	public void SetQuackAble(IQuackAble quackAble) {
		this.quackAble = quackAble;
	}
}
