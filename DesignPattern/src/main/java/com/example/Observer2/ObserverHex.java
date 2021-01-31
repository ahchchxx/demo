package com.example.Observer2;

public class ObserverHex extends Observer {
    public ObserverHex(Subject s) {
        this.subject = s;
        this.subject.attach(this);
    }

    @Override
    void update() {
        System.out.println("Hex:" + Integer.toHexString(this.subject.getState()));
    }
}