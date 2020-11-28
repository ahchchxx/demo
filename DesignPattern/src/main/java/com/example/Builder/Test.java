package com.example.Builder;

public class Test {

    public static void main(String[] args) {
        Builder builder = new MacBookBuilder();
        builder.buildBoard("英特尔主板");
        builder.buildDisplay("Retina显示器" );
        builder.buildOs();

        Computer computer = builder.build();
        System.out.println(computer.toString());
    }

}