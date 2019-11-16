package com.proky.booking.persistence.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserType userType = (UserType) o;
        return  Objects.equals(this.getId(), userType.getId()) &&
                Objects.equals(type, userType.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), type);
    }
}
