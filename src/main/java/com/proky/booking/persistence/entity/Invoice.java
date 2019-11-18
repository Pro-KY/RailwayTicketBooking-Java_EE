package com.proky.booking.persistence.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class Invoice extends Entity<Long> {
    private User user;
    private Train train;
    private Integer seatsAmount;
    private BigDecimal price;
    private Timestamp dateTime;

    public Invoice() {}

    public Invoice(Long id, User user, Train train, Integer seatsAmount, BigDecimal price, Timestamp dateTime) {
        super(id);
        this.user = user;
        this.train = train;
        this.seatsAmount = seatsAmount;
        this.price = price;
        this.dateTime = dateTime;
    }

    public Invoice(User user, Train train, Integer seatsAmount, BigDecimal price, Timestamp dateTime) {
        this.user = user;
        this.train = train;
        this.seatsAmount = seatsAmount;
        this.price = price;
        this.dateTime = dateTime;
    }

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

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return  Objects.equals(id, invoice.getId()) &&
                Objects.equals(user, invoice.user) &&
                Objects.equals(train, invoice.train) &&
                Objects.equals(seatsAmount, invoice.seatsAmount) &&
                Objects.equals(dateTime, invoice.dateTime) &&
                Objects.equals(price, invoice.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), user, train, seatsAmount, price, dateTime);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "user=" + user +
                ", train=" + train +
                ", seatsAmount=" + seatsAmount +
                ", price=" + price +
                ", dateTime=" + dateTime +
                ", id=" + id +
                '}';
    }
}
