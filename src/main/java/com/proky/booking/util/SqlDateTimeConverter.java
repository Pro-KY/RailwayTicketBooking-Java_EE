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
        int hours = 12;
        Time time;

        final String timeWithoutPM = timeUI.substring(0, timeUI.indexOf(" "));

        final String timePeriod = timeUI.substring(timeUI.indexOf(" ")).trim();
        if (timePeriod.equals("PM")) {
            final String[] splitTime = timeWithoutPM.split(timeSeparator);
            final int timePart = Integer.parseInt(splitTime[0]);
            final String minutesPart = splitTime[1];
            int PmHour = timePart + hours;
            time = Time.valueOf(PmHour + timeSeparator + minutesPart + timeSeparator + zeroSeconds);
        } else {
            time = Time.valueOf(timeWithoutPM + timeSeparator + zeroSeconds);
        }

        System.out.println(time);
        return time;
    }


//    public Time convertToSqlTime(String timeUI) {
//        String zeroSeconds = "00";
//        final String timeUISubstring = timeUI.substring(0, timeUI.indexOf(" "));
//        final Time time = Time.valueOf(timeUISubstring.concat(timeSeparator).concat(zeroSeconds));
//        System.out.println(time);
//        return time;
//    }

}
