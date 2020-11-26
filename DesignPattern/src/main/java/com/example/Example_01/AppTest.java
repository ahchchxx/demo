package com.example.Example_01;

public class AppTest {

	public static void main(String[] args) {
		// 用子类调用
//		MiniDuck miniDuck = new MiniDuck();
//		miniDuck.flyAble.Fly();
//		miniDuck.quackAble.Qucak();
//		
//		miniDuck.SetFlyAble(new FlyWithRock());
//		miniDuck.flyAble.Fly();
		
		// 用接口做“父类”
		Duck duck = new MiniDuck();
		duck.PerformFly();
		duck.PerformQuack();
		
		duck.SetFlyAble(new FlyWithRock());
		duck.PerformFly();
	}

}
