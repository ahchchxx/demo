package com.example.Factory_abstract;

public class AppTest {

	public static void main(String[] args) {
		Producer producer = new Producer1();
		Product p1 = producer.produce();
		p1.process();
		
		Producer producer2 = new Producer2();
		Product p2 = producer2.produce();
		p2.process();
	}
	
}
