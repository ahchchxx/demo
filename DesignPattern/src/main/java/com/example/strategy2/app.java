package com.example.strategy2;

public class app {
    public static void main(String[] args) {
        Duck duck = new ModelDuck();
        duck.display();
        duck.performQuack();
        duck.performFly();

        duck.setQuack(new DadaQuackImpl());
        duck.performQuack();
    }
}
