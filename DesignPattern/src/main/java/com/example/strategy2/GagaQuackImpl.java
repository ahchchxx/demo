package com.example.strategy2;

public class GagaQuackImpl implements IQuackBehavior {
    @Override
    public void quack() {
        System.out.println("I can guagua quack");
    }
}
