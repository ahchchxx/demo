package com.example.Factory;

/**
 * 工厂模式
 */
public abstract class PizzaStoreFactory {

	public void orderPizza(String typeName) {
		Pizza pizza;
		pizza = createPizza(typeName);
		
		pizza.prepare();
		pizza.bake();
		
	}
	public abstract Pizza createPizza(String typeName);
}
