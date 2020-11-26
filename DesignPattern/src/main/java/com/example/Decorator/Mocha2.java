package com.example.Decorator;

public class Mocha2 extends CondimentDecorator2 {
	public Mocha2(Beverage beverage) {
		super(beverage);
	}
	
	public String getDescription() {
		return beverage.getDescription() + ", Mocha";
	}

	@Override
	public double cost() {
		return 1.6 + beverage.cost();
	}

}
