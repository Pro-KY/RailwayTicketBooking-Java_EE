package com.proky.booking.util.properties;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class MessageProperties {
    private static ResourceBundle messageProperties;
    private static final String fileName = "message";
    public static final String USER_EXIST = "USER_EXIST";
    public static final String NOT_VALID_PROPERTY = "NOT_VALID_PROPERTY";
    public static final String NOT_FOUND_ENTITY = "NOT_FOUND_ENTITY";
    public static final String TRANSACTION_ERROR = "TRANSACTION_ERROR";
    public static final String AUTHORIZATION_ERROR = "AUTHORIZATION_ERROR";

    static {
        messageProperties = PropertyResourceBundle.getBundle(fileName);
    }

    public static String getMessage(String propertyName) {
           return messageProperties.getString(propertyName);
    }
}
