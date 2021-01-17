package com.example.command;

public class app {
    public static void main(String[] args) {
        Stock stock = new Stock("stock-b", 3);
        BuyOrder buyOrder = new BuyOrder(stock);
        SellOrder sellOrder = new SellOrder(stock);

        Broker broker = new Broker();
        broker.takeOrder(buyOrder);
        broker.takeOrder(sellOrder);

        broker.placeOrder();
    }
}
