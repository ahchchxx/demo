package com.example.Factory;

public class AppTest {

	public static void main(String[] args) {
//		PizzaStoreFactory pizzaStore = new PizzaStore_1();
//		pizzaStore.orderPizza("my favorate pizza, bacon pizza");
		
		PizzaStore_2 pizzaStore_2 = new PizzaStore_2();
		pizzaStore_2.orderPizza("pizza1");
		
		
//		Properties properties = System.getProperties();
//		for (Entry<Object, Object> set : properties.entrySet()) {
//			System.out.println(set.getKey() + "\t" + set.getValue());
//		}
	}

}
