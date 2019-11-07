package com.proky.booking.util.constans;

public enum UserTypeEnum {
    USER("passenger"), GUEST("guest"), ADMIN("administrator");

    public final String type;

    UserTypeEnum(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
