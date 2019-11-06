package com.proky.booking.util.constans;

public enum Exceptions {
    SERVICE_EXCEPTION("ServiceException");

    public final String name;

    private Exceptions(String name) {
        this.name = name;
    }
}
