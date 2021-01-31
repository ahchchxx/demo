package com.example.Observer2;

public class app {
    public static void main(String[] args) {
        Subject subject = new Subject();
        new ObserverBinary(subject);
        new ObserverHex(subject);

        // set state and nofify
        subject.setState(13);
    }
}
