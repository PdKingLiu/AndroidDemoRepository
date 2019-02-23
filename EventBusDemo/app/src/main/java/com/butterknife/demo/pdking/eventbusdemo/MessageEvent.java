package com.butterknife.demo.pdking.eventbusdemo;

public class MessageEvent {

    private String msg;

    public MessageEvent(String msg) {
        this.msg = msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
