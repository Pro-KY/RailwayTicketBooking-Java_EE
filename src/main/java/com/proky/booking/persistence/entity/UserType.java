package com.proky.booking.persistence.entity;

public class UserType extends Entity<Long> {
    private String type;

    public UserType(Long id) {
        super(id);
    }

    public UserType(Long id, String type) {
        super(id);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
