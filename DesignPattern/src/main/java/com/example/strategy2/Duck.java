package com.example.strategy2;

public abstract class Duck {
    private IFlyBehavior fly;
    private IQuackBehavior quack;

    public Duck(IFlyBehavior fly, IQuackBehavior quack) {
        this.fly = fly;
        this.quack=quack;
    }

    public abstract void display();

    public void performFly(){
        this.fly.fly();
    }
    public void performQuack(){
        this.quack.quack();
    }

    public void setFly(IFlyBehavior fly) {
        this.fly = fly;
    }
    public void setQuack(IQuackBehavior quack) {
        this.quack = quack;
    }
}
