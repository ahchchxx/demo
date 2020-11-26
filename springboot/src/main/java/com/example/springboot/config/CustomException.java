package com.example.springboot.config;

public class CustomException extends Exception {
    public RetBean codeMsg;  // 这个 bean，可以设置成 list 集合

    public CustomException(RetBean msg) {
        super(msg.getMsg());
        this.codeMsg = msg;
    }

}
