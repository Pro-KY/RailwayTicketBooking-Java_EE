package com.proky.booking.util.constans;

public enum UserType {
    USER("user"), GUEST("guest"), ADMIN("admin");

    public final String type;

    UserType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
