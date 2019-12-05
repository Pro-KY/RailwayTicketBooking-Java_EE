package com.proky.booking.util;

import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.validation.ValidationResult;

import javax.servlet.http.HttpServletRequest;

public class AlertHandler {
    private static AlertHandler mInstance;

    private AlertHandler() {}

    public static AlertHandler getInstance() {
        if (mInstance == null) {
            mInstance = new AlertHandler();
        }
        return mInstance;
    }

    public void setAlertAttributes(boolean alertSuccess, Object value, UrlBuilder urlBuilder) {
        String alertType = alertSuccess ? Attributes.ALERT_SUCCESS : Attributes.ALERT_ERROR;
        urlBuilder.setAttribute(alertType, true);
        urlBuilder.setAttribute(Attributes.ALERT_MESSAGE, value);
    }

    public void setAlertAttributes(HttpServletRequest request, String name, Object value) {
        request.setAttribute(name, true);
    }


    public void setAlertValidatonAttributes(HttpServletRequest request, ValidationResult validation) {
        request.setAttribute(Attributes.VALIDATION, validation);
    }
}
