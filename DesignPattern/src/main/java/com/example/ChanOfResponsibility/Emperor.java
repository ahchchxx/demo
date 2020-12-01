package com.example.ChanOfResponsibility;

public class Emperor {
    private final static Emperor emperor = new Emperor("xxx Emperor");
    private String name;

    private Emperor(String name) {
        this.name = name;
    }
    public static Emperor getInstance(){
        return emperor;
    }

    public String getName() {
        return name;
    }
}
