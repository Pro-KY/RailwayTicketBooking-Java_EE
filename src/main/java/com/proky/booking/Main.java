package com.proky.booking;

import com.proky.booking.util.SqlDateTimeConverter;

public class Main {
    public static void main(String[] args) {
        String dateUI = "11/10/2019";
        String timeUI = "4:05 AM";

        final SqlDateTimeConverter sqlDateTimeConverter = SqlDateTimeConverter.getInstance();
        sqlDateTimeConverter.convertToSqlDate(dateUI);
        sqlDateTimeConverter.convertToSqlTime(timeUI);


    }
}
