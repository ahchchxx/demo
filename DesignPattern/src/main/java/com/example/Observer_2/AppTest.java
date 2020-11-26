package com.example.Observer_2;

/**
 * 使用 Java 自带的观察者架构
 */
public class AppTest {

	public static void main(String[] args) {
		WeatherData subject = new WeatherData();
		
		DisplayA displayA = new DisplayA();
		//DisplayB displayB = new DisplayB();
		new DisplayB(subject);// 观察者，构造函数里面自动向“被观察者”注册列表
		
		subject.addObserver(displayA);
//		subject.addObserver(displayB);
		
		subject.setParams(1);
	}

}
