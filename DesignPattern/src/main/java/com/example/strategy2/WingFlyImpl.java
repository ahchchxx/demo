package com.example.strategy2;

public class WingFlyImpl implements IFlyBehavior {
    @Override
    public void fly() {
        System.out.println("I can fly with wings.");
    }
}
