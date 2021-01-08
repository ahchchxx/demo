package com.example.strategy2;

public class ModelDuck extends Duck {
    public ModelDuck() {
        super(new WingFlyImpl(), new GagaQuackImpl());
    }

    @Override
    public void display() {
        System.out.println("Hi, I am model duck.");
    }
}
