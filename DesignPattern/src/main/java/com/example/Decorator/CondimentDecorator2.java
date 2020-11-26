package com.example.Decorator;

/**
 * 这种方式，把一些操作放在装饰类上面了
 */
public abstract class CondimentDecorator2 extends Beverage {

	Beverage beverage;
	public CondimentDecorator2(Beverage beverage) {
		this.beverage = beverage;
	}
	
	public abstract double cost();

}
