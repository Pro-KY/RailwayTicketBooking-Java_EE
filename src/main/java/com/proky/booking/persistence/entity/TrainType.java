package com.proky.booking.persistence.entity;
import java.math.BigDecimal;

public class TrainType extends Entity<Long> {
    private String type;
    private BigDecimal seatPrice;

    public TrainType(Long id) {
        super(id);
    }

    public TrainType(Long id, String type, BigDecimal seatPrice) {
        super(id);
        this.type = type;
        this.seatPrice = seatPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(BigDecimal seatPrice) {
        this.seatPrice = seatPrice;
    }

    @Override
    public String toString() {
        return "TrainType{" +
                "type='" + type + '\'' +
                ", seatPrice=" + seatPrice +
                '}';
    }
}
