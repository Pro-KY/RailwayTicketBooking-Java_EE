package com.proky.booking.util.properties;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class SqlProperties {
    private static ResourceBundle viewPathProperties;
    private static final String fileName = "sql";

    // USER
    public static final String FIND_USER_BY_EMAIL = "FIND_USER_BY_EMAIL";
    public static final String SAVE_USER = "SAVE_USER";
    public static final String UPDATE_USER = "UPDATE_USER";
    public static final String DELETE_USER_BY_ID = "DELETE_USER_BY_ID";
    public static final String FIND_ALL_USERS_BY_TYPE = "FIND_ALL_USERS_BY_TYPE";
    public static final String COUNT_ALL_USERS_BY_TYPE = "COUNT_ALL_USERS_BY_TYPE";
    public static final String FIND_USER_BY_ID = "FIND_USER_BY_ID";

    // USER_TYPE
    public static final String FIND_USER_TYPE_BY_TYPE = "FIND_USER_TYPE_BY_TYPE";
    public static final String UPDATE_USER_TYPE_BY_ID = "UPDATE_USER_TYPE_BY_ID";
    public static final String DELETE_USER_TYPE_BY_ID = "DELETE_USER_TYPE_BY_ID";
    public static final String SAVE_USER_TYPE = "SAVE_USER_TYPE";
    public static final String FIND_USER_TYPE_BY_ID = "FIND_USER_TYPE_BY_ID";

    // STATION
    public static final String FIND_ALL_STATIONS = "FIND_ALL_STATIONS";
    public static final String FIND_STATION_BY_ID = "FIND_STATION_BY_ID";
    public static final String UPDATE_STATION_BY_ID = "UPDATE_STATION_BY_ID";
    public static final String DELETE_STATION_BY_ID = "DELETE_STATION_BY_ID";
    public static final String SAVE_STATION = "SAVE_STATION";

    // TRAIN
    public static final String FIND_TRAIN_BY_ID = "FIND_TRAIN_BY_ID";
    public static final String FIND_TRAINS_BY_DATE_TIME_STATION = "FIND_TRAINS_BY_DATE_TIME_STATION";
    public static final String COUNT_TRAINS_BY_DATE_TIME_STATION = "COUNT_TRAINS_BY_DATE_TIME_STATION";
    public static final String UPDATE_TRAIN_BY_ID = "UPDATE_TRAIN_BY_ID";
    public static final String DELETE_TRAIN_BY_ID = "DELETE_TRAIN_BY_ID";
    public static final String SAVE_TRAIN = "SAVE_TRAIN";

    // INVOICE
    public static final String SAVE_INVOICE = "SAVE_INVOICE";
    public static final String UPDATE_INVOICE = "UPDATE_INVOICE";
    public static final String DELETE_INVOICE = "DELETE_INVOICE";
    public static final String FIND_INVOICE_BY_ID = "FIND_INVOICE_BY_ID";

    // ROUTE_STATION
    public static final String FIND_ROUTE_STATION_BY_ROUTE_ID = "FIND_ROUTE_STATION_BY_ROUTE_ID";
    public static final String UPDATE_ROUTE_STATION_BY_ID = "UPDATE_ROUTE_STATION_BY_ID";
    public static final String DELETE_ROUTE_STATION_BY_ID = "DELETE_ROUTE_STATION_BY_ID";
    public static final String SAVE_ROUTE_STATION = "SAVE_ROUTE_STATION";
    public static final String FIND_ROUTE_STATION_BY_ID = "FIND_ROUTE_STATION_BY_ID";

    // ROUTE
    public static final String SAVE_ROUTE = "SAVE_ROUTE";
    public static final String UPDATE_ROUTE_BY_ID = "UPDATE_ROUTE_BY_ID";
    public static final String DELETE_ROUTE_BY_ID = "DELETE_ROUTE_BY_ID";
    public static final String FIND_ROUTE_BY_ID = "FIND_ROUTE_BY_ID";



    static {
        viewPathProperties = PropertyResourceBundle.getBundle(fileName);
    }

    public static String getValue(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException(MessageProperties.NOT_VALID_PROPERTY);
        }

       return viewPathProperties.getString(key);
    }
}
