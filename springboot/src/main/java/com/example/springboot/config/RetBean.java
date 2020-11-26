package com.example.springboot.config;

public class RetBean<T> {
    private String code;
    private String msg;
    private T data;

    public RetBean(String code, String msg, T data) {
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
    }

    public static RetBean GetBean(String code, String msg) {
        return new RetBean(code, msg, null);
    }
    public static <T> RetBean<T> success(T data) {
        return new RetBean("200", "Success", data);
    }
    public static <T> RetBean<T> fail(T data) {
        return new RetBean("400", "Fail", data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
