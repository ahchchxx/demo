package com.example.command;

public class Stock {
    private String name = "Stock-A";
    private Integer quantity = 10;

    Stock(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public void buy() {
        System.out.println("Bought " + quantity + " " + name);
    }

    public void sell() {
        System.out.println("Sold " + quantity + " " + name);
    }
}
