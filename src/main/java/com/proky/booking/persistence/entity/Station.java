package com.proky.booking.persistence.entity;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return  Objects.equals(name, station.name) &&
                Objects.equals(this.getId(), station.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), name);
    }
}
