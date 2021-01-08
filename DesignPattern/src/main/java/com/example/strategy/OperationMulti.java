package com.example.strategy;

public class OperationMulti implements Strategy {
    @Override
    public int operate(int num1, int num2) {
        return num1 * num2;
    }
}
