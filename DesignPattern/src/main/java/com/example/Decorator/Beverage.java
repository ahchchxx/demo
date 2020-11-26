package com.example.Decorator;

public abstract class Beverage {
	
	String description = "unkown beverage";
	public String getDescription() {
		return description;
	}
	
	public abstract double cost();
}
