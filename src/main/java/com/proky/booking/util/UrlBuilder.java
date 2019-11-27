package com.proky.booking.util;
import com.proky.booking.util.constans.http.Attributes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.proky.booking.util.constans.http.Commands.REDIRECT;

public class UrlBuilder {
    private static final Logger log = LogManager.getLogger(UrlBuilder.class);

    private StringBuilder sb = new StringBuilder();
    private Map<String, Object> attributes = new HashMap<>();
    private String viewPath;
    private boolean redirect;

    public UrlBuilder(String viewPath) {
        this.viewPath = viewPath;
    }


    public UrlBuilder(boolean redirect, String viewPath) {
        this.viewPath = viewPath;
        this.redirect = redirect;
    }

    public UrlBuilder(String viewPath, Map<String, Object> attributes) {
        this.viewPath = viewPath;
        this.attributes = attributes;
    }

    public UrlBuilder(boolean redirect, String viewPath, Map<String, Object> attributes) {
        this.viewPath = viewPath;
        this.attributes = attributes;
        this.redirect = redirect;
    }

    public void setViewPath(String viewPath) {
        this.viewPath = viewPath;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    public void setAlertParameters(boolean alertSuccess, String alertMessage) {
        String alertType = alertSuccess ? Attributes.ALERT_SUCCESS : Attributes.ALERT_ERROR;
        setAttribute(alertType, true);
        setAttribute(Attributes.ALERT_MESSAGE, alertMessage);
    }

    public String buildURL() {
        if (redirect) {
            sb.append(REDIRECT);
        }

        sb.append(viewPath);

        if (attributes.size() > 0) {
            sb.append("?");

            boolean isFirst = true;
            for (Map.Entry<String, Object> entry : attributes.entrySet()) {
                String parameter = entry.getKey();
                Object value = entry.getValue();

                if (isFirst) {
                    sb.append(parameter).append("=").append(value);
                    isFirst = false;
                } else {
                    sb.append("&").append(parameter).append("=").append(value);
                }
            }
        }

        return sb.toString();
    }
}
