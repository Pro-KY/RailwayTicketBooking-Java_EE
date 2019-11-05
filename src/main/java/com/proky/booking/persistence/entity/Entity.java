package com.proky.booking.persistence.entity;

import java.io.Serializable;

public class Entity<T> implements Serializable {
    private T id;

    public Entity() {}

    public Entity(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
