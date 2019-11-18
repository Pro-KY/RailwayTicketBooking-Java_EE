package com.proky.booking.util.properties;

import com.proky.booking.exception.GlobalExceptionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class MessageProperties {
    private static final Logger log = LogManager.getLogger(MessageProperties.class);

    private static ResourceBundle messageProperties;
    private static final String fileName = "message";
    public static final String USER_EXIST = "USER_EXIST";
    public static final String NOT_VALID_PROPERTY = "NOT_VALID_PROPERTY";
    public static final String NOT_FOUND_ENTITY = "NOT_FOUND_ENTITY";
    public static final String TRANSACTION_ERROR = "TRANSACTION_ERROR";
    public static final String AUTHORIZATION_ERROR = "AUTHORIZATION_ERROR";
    public static final String USER_UPDATED = "USER_UPDATED";
    public static final String USER_DELETED = "USER_DELETED";
    public static final String USER_CREATED = "USER_CREATED";

    static {
        messageProperties = PropertyResourceBundle.getBundle(fileName);
    }

    public static String getMessage(String propertyName) {
        String propertyValue = "";

        try {
            propertyValue =  messageProperties.getString(propertyName);
        } catch (MissingResourceException e) {
            log.error("could not find property for propertyName - {}, ", propertyName);
        }

        return propertyValue;
    }
}
