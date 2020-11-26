package com.example.Decorator;

public class OrangeJuice extends Beverage {

	public OrangeJuice() {
		description = "orange juice beverage";
	}

	@Override
	public double cost() {
		return 2;
	}

}
