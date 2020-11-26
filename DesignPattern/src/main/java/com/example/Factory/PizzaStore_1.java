package com.example.Factory;

public class PizzaStore_1 extends PizzaStoreFactory {

	@Override
	public Pizza createPizza(String typeName) {
		System.out.println("create pizza: "+ typeName);
		if ("pizza1".equals(typeName)) {
			return new Pizza_1();
		}
		return null;
	}
}
