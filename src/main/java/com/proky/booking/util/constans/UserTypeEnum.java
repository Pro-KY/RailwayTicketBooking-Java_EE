package com.proky.booking.util.constans;

public enum UserTypeEnum {
    USER("user"), GUEST("guest"), ADMIN("admin");

    public final String type;

    UserTypeEnum(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
