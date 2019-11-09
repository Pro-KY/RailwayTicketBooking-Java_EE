package com.proky.booking;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ResourceBundle messageProperties = PropertyResourceBundle.getBundle("i18n/messages_en");
        if (messageProperties !=null) {
            final Enumeration<String> keys = messageProperties.getKeys();
            Map<String, String> stationsMap = new HashMap<>();
            int stationWordLength = "station".length();

            while (keys.hasMoreElements()) {

                final String key = keys.nextElement();
                if (key.startsWith("station")) {
                    String index = key.substring(stationWordLength+1);
                    stationsMap.put(index, key);
                }
            }

            stationsMap.forEach((k,v) -> {
                System.out.println(k + "-" +v);
            });
        }
    }
}
