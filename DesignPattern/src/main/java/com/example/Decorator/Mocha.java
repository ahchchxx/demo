package com.example.Decorator;

public class Mocha extends CondimentDecorator {

	Beverage beverage;
	public Mocha(Beverage beverage) {
		this.beverage = beverage;
	}
	
	public String getDescription() {
		return beverage.getDescription() + ", Mocha";
	}

	@Override
	public double cost() {
		return 1.6 + beverage.cost();
	}

}
