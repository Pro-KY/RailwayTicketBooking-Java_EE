package com.proky.booking.persistence.entity;
import java.math.BigDecimal;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainType trainType = (TrainType) o;
        return  Objects.equals(this.getId(), trainType.getId()) &&
                Objects.equals(type, trainType.type) &&
                Objects.equals(seatPrice, trainType.seatPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), type, seatPrice);
    }

    @Override
    public String toString() {
        return "TrainType{" +
                "type='" + type + '\'' +
                ", seatPrice=" + seatPrice +
                '}';
    }
}
