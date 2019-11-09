package com.proky.booking.util.constans;

public enum LocaleEnum {
    UA("ua"), EN("en");

    private String value;

    LocaleEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
