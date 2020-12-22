package com.etsoft.ETEnglish.iCloud;

public class Msg {
    public int code;
    public String msg;

    public Msg(int code, String msg) {
        setCode(code);
        setMsg(msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
