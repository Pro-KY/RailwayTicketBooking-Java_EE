package com.proky.booking.util.properties;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ViewProperties {
    private static ResourceBundle viewPathProperties;
    private static final String fileName = "view";

    public static final String FRAGMENT_SIGN_UP = "FRAGMENT_SIGN_UP";
    public static final String FRAGMENT_SIGN_IN = "FRAGMENT_SIGN_IN";
    public static final String FRAGMENT_MAIN = "FRAGMENT_MAIN";
    public static final String INDEX = "INDEX";
    public static final String ERROR = "ERROR";

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
