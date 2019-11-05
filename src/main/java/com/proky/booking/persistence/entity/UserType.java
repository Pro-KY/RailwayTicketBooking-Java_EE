package com.proky.booking.persistence.entity;

public class UserType extends Entity<Long> {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
