package com.proky.booking.util.form;

import static com.proky.booking.util.constans.http.Parameters.*;

public class FindTrainForm {
    private String goingTo;
    private String departureDate;
    private String departureTime;

    public FindTrainForm(String goingTo, String departureDate, String departureTime) {
        this.goingTo = goingTo;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
    }

    public String getGoingTo() {
        return goingTo;
    }

    public void setGoingTo(String goingTo) {
        this.goingTo = goingTo;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
}
