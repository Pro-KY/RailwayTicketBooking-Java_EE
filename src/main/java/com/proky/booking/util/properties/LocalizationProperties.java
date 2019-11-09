package com.proky.booking.util.properties;

import com.proky.booking.util.constans.LocaleEnum;

import java.util.*;

public class LocalizationProperties {
    private final static String localeFileNamePath = "i18n/messages_";
    private ResourceBundle localizationPathProperties;

    public LocalizationProperties(LocaleEnum localeEnum) {
        String s = localeFileNamePath + localeEnum;
        localizationPathProperties = PropertyResourceBundle.getBundle(localeFileNamePath + localeEnum);
    }

    public Map<String, String> getStations() {
        String stationKeyPart = "station";

        final Enumeration<String> keys = localizationPathProperties.getKeys();
        Map<String, String> stationsMap = new HashMap<>();
        int stationKeyPartLength = stationKeyPart.length();

        while (keys.hasMoreElements()) {
            final String key = keys.nextElement();
            if (key.startsWith(stationKeyPart)) {
                String index = key.substring(stationKeyPartLength + 1);
                stationsMap.put(index, key);
            }
        }

        return stationsMap;
    }

    public String getValue(String propertyName) {
        if (propertyName == null || propertyName.isEmpty()) {
            throw new IllegalArgumentException("propertyName can't be null or empty!");
        }

        return localizationPathProperties.getString(propertyName);
    }
}
