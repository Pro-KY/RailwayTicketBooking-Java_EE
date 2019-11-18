package com.proky.booking.util.properties;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class SqlProperties {
    private static ResourceBundle viewPathProperties;
    private static final String fileName = "sql";
    public static final String FIND_USER_BY_EMAIL = "FIND_USER_BY_EMAIL";
    public static final String FIND_USER_TYPE_BY_TYPE = "FIND_USER_TYPE_BY_TYPE";
    public static final String SAVE_USER = "SAVE_USER";
    public static final String FIND_ROUTE_STATION_BY_ROUTE_ID = "FIND_ROUTE_STATION_BY_ROUTE_ID";
    public static final String UPDATE_USER = "UPDATE_USER";
    public static final String DELETE_USER_BY_ID = "DELETE_USER_BY_ID";

    public static final String FIND_TRAINS_BY_DATE_TIME_STATION = "FIND_TRAINS_BY_DATE_TIME_STATION";
    public static final String COUNT_TRAINS_BY_DATE_TIME_STATION = "COUNT_TRAINS_BY_DATE_TIME_STATION";
    public static final String FIND_ALL_STATIONS = "FIND_ALL_STATIONS";
    public static final String FIND_TRAIN_BY_ID = "FIND_TRAIN_BY_ID";

    public static final String FIND_ALL_USERS_BY_TYPE = "FIND_ALL_USERS_BY_TYPE";
    public static final String COUNT_ALL_USERS_BY_TYPE = "COUNT_ALL_USERS_BY_TYPE";
    public static final String FIND_USER_BY_ID = "FIND_USER_BY_ID";

    public static final String SAVE_INVOICE = "SAVE_INVOICE";

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
