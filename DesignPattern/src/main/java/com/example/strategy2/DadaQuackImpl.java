package com.example.strategy2;

public class DadaQuackImpl implements IQuackBehavior {
    @Override
    public void quack() {
        System.out.println("I can dada quack");
    }
}
