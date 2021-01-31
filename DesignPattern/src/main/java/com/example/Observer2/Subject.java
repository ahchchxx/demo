package com.example.Observer2;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    List<Observer> list = new ArrayList<>();
    int state;

    public void attach(Observer o){
        list.add(o);
    }
    public void notifyAllObserver() {
        for (Observer o : list) {
            o.update();
        }
    }

    public int getState() {
        return state;
    }
    public void setState(int state) {
        System.out.println("Set octal:" + state);
        this.state = state;
        notifyAllObserver();
    }
}
