package com.proky.booking.util.properties;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class SqlProperties {
    private static ResourceBundle viewPathProperties;
    private static final String fileName = "sql";
    public static final String FIND_USER_BY_EMAIL = "FIND_USER_BY_EMAIL";
    public static final String FIND_USER_TYPE_BY_TYPE = "FIND_USER_TYPE_BY_TYPE";
    public static final String SAVE_USER = "SAVE_USER";

    public static final String FIND_TRAINS_BY_DATE_TIME_STATION = "FIND_TRAINS_BY_DATE_TIME_STATION";

    static {
        viewPathProperties = PropertyResourceBundle.getBundle(fileName);
    }

    public static String getQuery(String propertyName) {
        if (propertyName == null || propertyName.isEmpty()) {
            throw new IllegalArgumentException(MessageProperties.NOT_VALID_PROPERTY);
        }

       return viewPathProperties.getString(propertyName);
    }
}
