package com.example.command;

public class BuyOrder implements Order {
    Stock stock;
    BuyOrder(Stock stock) {
        this.stock =stock;
    }
    @Override
    public void execute() {
        this.stock.buy();
    }
}
