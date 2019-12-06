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
    private String contextPath;

    public UrlBuilder(String viewPath) {
        this.viewPath = viewPath;
    }


    public UrlBuilder(boolean redirect, String contextPath, String viewPath) {
        this.viewPath = viewPath;
        this.redirect = redirect;
        this.contextPath = contextPath;
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

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
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

    public String buildURL() {
        if (redirect) {
            sb.append(REDIRECT);
        }

        if (contextPath != null) {
            sb.append(contextPath);
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
