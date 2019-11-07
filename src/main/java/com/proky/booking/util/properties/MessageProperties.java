package com.proky.booking.util.properties;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class MessageProperties {
    private static ResourceBundle messageProperties;
    private static final String fileName = "message";
    public static final String USER_EXIST;
    public static final String NOT_VALID_PROPERTY;

    static {
        messageProperties = PropertyResourceBundle.getBundle(fileName);
        USER_EXIST = getMessage("userAlreadyExist");
        NOT_VALID_PROPERTY = getMessage("notValidPropertyName");
    }

    private static String getMessage(String propertyName) {
           return messageProperties.getString(propertyName);
    }
}
