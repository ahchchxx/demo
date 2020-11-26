package com.example.Observer_1;

public class AppTest {

	public static void main(String[] args) {
		WeatherData subject = new WeatherData();
		
		DisplayA displayA = new DisplayA();
		subject.register(displayA);
		subject.register(new DisplayB());
		
		subject.remove(displayA);
		subject.remove(null);
		subject.remove(displayA);
		
		subject.setParams(1);
	}

}
