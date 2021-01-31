package com.example.Observer2;

public class ObserverBinary extends Observer {
    public ObserverBinary(Subject s){
        this.subject = s;
        this.subject.attach(this);
    }

    @Override
    void update() {
        System.out.println("Binary:" + Integer.toBinaryString(this.subject.getState()));
    }
}
