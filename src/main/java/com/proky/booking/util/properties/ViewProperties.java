package com.proky.booking.util.properties;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ViewProperties {
    private static ResourceBundle viewPathProperties;
    private static final String fileName = "view";

    public static final String FRAGMENT_SIGN_UP;
    public static final String FRAGMENT_MAIN;
    public static final String INDEX;
    public static final String ERROR;

    static {
        viewPathProperties = PropertyResourceBundle.getBundle(fileName);

        FRAGMENT_SIGN_UP = getPath("fragment.sign.up");
        FRAGMENT_MAIN = getPath("fragment.main");
        INDEX = getPath("index");
        ERROR = getPath("error");
    }

    private static String getPath(String propertyName) {
        if (propertyName == null || propertyName.isEmpty()) {
            throw new IllegalArgumentException("propertyName can't be null or empty!");
        }

       return viewPathProperties.getString(propertyName);
    }
}
