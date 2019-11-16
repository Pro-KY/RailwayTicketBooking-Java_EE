package com.proky.booking.persistence.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Invoice extends Entity {
    private User user;
    private Train train;
    private Integer seatsAmount;
    private BigDecimal price;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Integer getSeatsAmount() {
        return seatsAmount;
    }

    public void setSeatsAmount(Integer seatsAmount) {
        this.seatsAmount = seatsAmount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return  Objects.equals(this.getId(), invoice.getId()) &&
                Objects.equals(user, invoice.user) &&
                Objects.equals(train, invoice.train) &&
                Objects.equals(seatsAmount, invoice.seatsAmount) &&
                Objects.equals(price, invoice.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), user, train, seatsAmount, price);
    }
}
