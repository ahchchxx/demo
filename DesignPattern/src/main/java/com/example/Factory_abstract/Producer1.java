package com.example.Factory_abstract;

public class Producer1 implements Producer {

	public Product produce() {
		return new Product1();
	}

}
