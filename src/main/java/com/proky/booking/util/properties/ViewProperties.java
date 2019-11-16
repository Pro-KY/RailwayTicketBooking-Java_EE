package com.proky.booking.util.properties;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ViewProperties {
    private static ResourceBundle viewPathProperties;
    private static final String fileName = "view";

    public static final String SIGN_UP = "SIGN_UP";
    public static final String SIGN_IN = "SIGN_IN";
    public static final String TRAIN_BOOKING = "TRAIN_BOOKING";
    public static final String INVOICE = "INVOICE";

    public static final String INDEX = "INDEX";
    public static final String ERROR = "ERROR";

    public static final String ADMIN_USERS = "ADMIN_USERS";
    public static final String ADMIN_MANAGE_USER = "ADMIN_MANAGE_USER";

    static {
        viewPathProperties = PropertyResourceBundle.getBundle(fileName);
    }

    public static String getPath(String propertyName) {
        if (propertyName == null || propertyName.isEmpty()) {
            throw new IllegalArgumentException("propertyName can't be null or empty!");
        }

       return viewPathProperties.getString(propertyName);
    }
}
