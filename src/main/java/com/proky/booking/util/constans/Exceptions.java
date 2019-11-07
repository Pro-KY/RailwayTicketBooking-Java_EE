package com.proky.booking.util.constans;

public enum Exceptions {
    SERVICE_EXCEPTION("ServiceException");

    public final String name;

    Exceptions(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
