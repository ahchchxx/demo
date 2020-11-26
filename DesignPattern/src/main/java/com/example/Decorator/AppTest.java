package com.example.Decorator;

public class AppTest {

	public static void main(String[] args) {
		
		/*
		 * Beverage beverage = new OrangeJuice(); // beverage OrangeJuice (id=20) //
		 * description "orange juice beverage" (id=22)
		 * System.out.println(beverage.getDescription() + " $" + beverage.cost());
		 */

		
//		Beverage beverage2 = new OrangeJuice();
//		beverage2 = new Mocha(beverage2);
//		beverage2 = new Mocha(beverage2);
//		System.out.println(beverage2.getDescription() + " $" + beverage2.cost());
		
//		Beverage beverage2 = new OrangeJuice();
//		beverage2 = new Mocha(beverage2);
////		beverage2	Mocha  (id=30)	
////			beverage	OrangeJuice  (id=22)	
////				description	"orange juice beverage" (id=23)	
////			description	"unkown beverage" (id=32)	
//
//		beverage2 = new Mocha(beverage2);
////		beverage2	Mocha  (id=80)	
////			beverage	Mocha  (id=68)	
////				beverage	OrangeJuice  (id=64)	
////					description	"orange juice beverage" (id=22)	
////				description	"unkown beverage" (id=70)	
////			description	"unkown beverage" (id=70)	
//		System.out.println(beverage2.getDescription() + " $" + beverage2.cost());
		
		
		Beverage beverage22 = new OrangeJuice();
		beverage22 = new Mocha2(beverage22);
		beverage22 = new Mocha2(beverage22);
		System.out.println(beverage22.getDescription() + " $" + beverage22.cost());
		// Mocha2 是装饰器的子类，包裹了一个 Beverage 类型的变量
		// OrangeJuice 是 Beverage 接口的实现类，被包在了最里面
//		beverage22	Mocha2  (id=17)	
//			beverage	Mocha2  (id=23)	
//				beverage	OrangeJuice  (id=28)	
//					description	"orange juice beverage" (id=31)	
//				description	"unkown beverage" (id=24)	
//			description	"unkown beverage" (id=24)	

	}
}
