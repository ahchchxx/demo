package com.example.command;

import java.util.ArrayList;
import java.util.List;

public class Broker {
    private List<Order> list = new ArrayList<>();

    public void takeOrder(Order order) {
        list.add(order);
    }
    public void placeOrder() {
        for (Order order : list) {
            order.execute();
        }
        list.clear();
    }
}
