package com.example.Factory_abstract;

public class Producer2 implements Producer {

	@Override
	public Product produce() {
		return new Product2();
	}

}
