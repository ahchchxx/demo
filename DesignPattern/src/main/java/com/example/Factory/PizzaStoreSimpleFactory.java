package com.example.Factory;

/**
 * 简单工厂模式，根据传入的参数决定，初始化什么样的实例
 */
public class PizzaStoreSimpleFactory {
	public void orderPizza(String typeName) {
		Pizza pizza;
		pizza = createPizza(typeName);
		
		pizza.prepare();
		pizza.bake();
	}
	public Pizza createPizza(String typeName) {
		if ("pizza1".equals(typeName)) {
			System.out.println("create pizza: "+ typeName);
			return new Pizza_1();
		}
		return null;
	}
}
