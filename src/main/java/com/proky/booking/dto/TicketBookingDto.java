package com.proky.booking.dto;

import com.proky.booking.validation.annotation.Length;
import com.proky.booking.validation.annotation.Size;

import java.io.Serializable;

public class TicketBookingDto implements Serializable {
    private String trainId;

    @Length(min = 3, max = 20)
    private String firstName;

    @Length(min = 3, max = 20)
    private String lastName;

    @Size(max = 3)
    private String seatsAmount;

    public TicketBookingDto() {}

    private TicketBookingDto(Builder builder) {
        setTrainId(builder.trainId);
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        setSeatsAmount(builder.seatsAmount);
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSeatsAmount() {
        return seatsAmount;
    }

    public void setSeatsAmount(String seatsAmount) {
        this.seatsAmount = seatsAmount;
    }

    public static final class Builder {
        private String trainId;
        private String firstName;
        private String lastName;
        private String seatsAmount;

        public Builder() {
        }

        public Builder trainId(String val) {
            trainId = val;
            return this;
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public Builder seatsAmount(String val) {
            seatsAmount = val;
            return this;
        }

        public TicketBookingDto build() {
            return new TicketBookingDto(this);
        }
    }

    @Override
    public String toString() {
        return "TicketBookingDto{" +
                "trainId=" + trainId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", seats=" + seatsAmount +
                '}';
    }
}
