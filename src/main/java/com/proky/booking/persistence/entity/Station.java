package com.proky.booking.persistence.entity;


public class Station extends Entity<Long> {
    private String name;

    public Station(Long id) {
        super(id);
    }

    public Station(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
