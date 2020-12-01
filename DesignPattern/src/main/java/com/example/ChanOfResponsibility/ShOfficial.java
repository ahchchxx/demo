package com.example.ChanOfResponsibility;

public class ShOfficial extends Official {

    public ShOfficial() {
        this.title = "上海总督";
    }

    @Override
    public void serve(Emperor emperor) {
        System.out.println(this.title + " serve " + emperor.getName());
    }

}
