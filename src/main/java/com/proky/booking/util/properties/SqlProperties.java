package com.proky.booking.util.properties;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class SqlProperties {
    private static ResourceBundle viewPathProperties;
    private static final String fileName = "sql";
    public static final String FIND_BY_EMAIL;

    static {
        viewPathProperties = PropertyResourceBundle.getBundle(fileName);
        FIND_BY_EMAIL = getQuery("findByEmail");
    }

    private static String getQuery(String propertyName) {
        if (propertyName == null || propertyName.isEmpty()) {
            throw new IllegalArgumentException(MessageProperties.NOT_VALID_PROPERTY);
        }

       return viewPathProperties.getString(propertyName);
    }
}
