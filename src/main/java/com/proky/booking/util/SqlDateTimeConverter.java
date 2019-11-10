package com.proky.booking.util;

import java.sql.Date;
import java.sql.Time;

public class SqlDateTimeConverter {
    private final static String timeSeparator = ":";
    private final static String dateSeparator = "-";
    private final static String whiteSpaceChar = " ";
    private static SqlDateTimeConverter mInstance;

    private SqlDateTimeConverter() {}

    public static SqlDateTimeConverter getInstance() {
        if (mInstance == null) {
            mInstance = new SqlDateTimeConverter();
        }
        return mInstance;
    }

    public Date convertToSqlDate(String dateUI) {
        final String[] splitDate = dateUI.replace("/", whiteSpaceChar).split(whiteSpaceChar);
        String formated = splitDate[2].concat(dateSeparator).concat(splitDate[0]).concat(dateSeparator).concat(splitDate[1]);
        System.out.println(formated);
        return Date.valueOf(formated);
    }

    public Time convertToSqlTime(String timeUI) {
        String zeroSeconds = "00";
        final String timeUISubstring = timeUI.substring(0, timeUI.indexOf(" "));
        final Time time = Time.valueOf(timeUISubstring.concat(timeSeparator).concat(zeroSeconds));
        System.out.println(time);
        return time;
    }

}
