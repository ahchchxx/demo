package com.example.event;

public class app {

    public static void main(String[] args) throws InterruptedException {
        MethodMonitorEventPublisher publisher = new MethodMonitorEventPublisher();

        publisher.addEventListener(new AbstractMethodMonitorEventListener());

        publisher.methodMonitor();
    }

}
