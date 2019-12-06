package com.proky.booking.validation;

import java.util.Arrays;

public class ErrorMessage {
    private String msgKey;
    private Object[] params;

    public ErrorMessage() {}

    public ErrorMessage(String msgKey, Object[] params) {
        this.msgKey = msgKey;
        this.params = params;
    }

    public ErrorMessage(String msgKey) {
        this.msgKey = msgKey;
    }

    public String getMsgKey() {
        return msgKey;
    }

    public void setMsgKey(String msgKey) {
        this.msgKey = msgKey;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msgKey='" + msgKey + '\'' +
                ", params=" + Arrays.toString(params) +
                '}';
    }

}
