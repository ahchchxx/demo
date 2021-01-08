package com.example.strategy;

public class Context {
    private Strategy strategy;

    // private Context(){}

    // constructor, no "void" or return value's type
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int doOperate(int num1, int num2) {
        return this.strategy.operate(num1, num2);
    }

}
