package com.example.command;

public class SellOrder implements Order {
    Stock stock;
    SellOrder(Stock stock) {
        this.stock =stock;
    }
    @Override
    public void execute() {
        this.stock.sell();
    }
}
