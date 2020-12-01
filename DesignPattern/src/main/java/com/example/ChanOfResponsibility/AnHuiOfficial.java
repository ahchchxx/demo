package com.example.ChanOfResponsibility;

public class AnHuiOfficial extends Official {

    public AnHuiOfficial() {
        this.title = "安徽总督";
    }

    @Override
    public void serve(Emperor emperor) {
        System.out.println(this.title + " serve " + emperor.getName());
    }

}
