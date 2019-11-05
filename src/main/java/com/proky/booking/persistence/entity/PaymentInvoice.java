package com.proky.booking.persistence.entity;

import java.math.BigDecimal;

public class PaymentInvoice extends Entity {
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
}
