package com.proky.booking.util;
import com.proky.booking.exception.GlobalExceptionHandler;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.properties.MessageProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.proky.booking.util.constans.Commands.REDIRECT;
import static com.proky.booking.util.properties.MessageProperties.USER_CREATED;

public class URLBuilder {
    private static final Logger log = LogManager.getLogger(URLBuilder.class);

    private StringBuilder sb = new StringBuilder();
    private Map<String, Object> parameters = new HashMap<>();
    private String viewPath;
    private boolean redirect;

    public URLBuilder(String viewPath) {
        this.viewPath = viewPath;
    }


    public URLBuilder(boolean redirect, String viewPath) {
        this.viewPath = viewPath;
        this.redirect = redirect;
    }

    public URLBuilder(String viewPath, Map<String, Object> parameters) {
        this.viewPath = viewPath;
        this.parameters = parameters;
    }

    public URLBuilder(boolean redirect, String viewPath, Map<String, Object> parameters) {
        this.viewPath = viewPath;
        this.parameters = parameters;
        this.redirect = redirect;
    }

    public void setViewPath(String viewPath) {
        this.viewPath = viewPath;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

    public void setParameter(String name, Object value) {
        parameters.put(name, value);
    }

    public void setAlertParameters(boolean alertSuccess, String alertMessage) {
        String alertType = alertSuccess ? Attributes.ALERT_SUCCESS : Attributes.ALERT_ERROR;
        setParameter(alertType, true);
        setParameter(Attributes.ALERT_MESSAGE, alertMessage);
    }

    public String buildURL() {
        if (redirect) {
            sb.append(REDIRECT);
        }

        sb.append(viewPath);

        if (parameters.size() > 0) {
            sb.append("?");

            boolean isFirst = true;
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
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
