package com.proky.booking.util;
import java.util.HashMap;
import java.util.Map;

import static com.proky.booking.util.constans.Commands.REDIRECT;

public class URLBuilder {
    private StringBuilder sb = new StringBuilder();
    private String viewPath;
    private Map<String, String> parameters = new HashMap<>();
    private boolean redirect;

    public URLBuilder(String viewPath) {
        this.viewPath = viewPath;
    }


    public URLBuilder(boolean redirect, String viewPath) {
        this.viewPath = viewPath;
        this.redirect = redirect;
    }

    public URLBuilder(String viewPath, Map<String, String> parameters) {
        this.viewPath = viewPath;
        this.parameters = parameters;
    }

    public URLBuilder(boolean redirect, String viewPath, Map<String, String> parameters) {
        this.viewPath = viewPath;
        this.parameters = parameters;
        this.redirect = redirect;
    }

    public void setAttribute(String name, String value) {
        parameters.put(name, value);
    }

    public String buildURL() {
        if (redirect) {
            sb.append(REDIRECT);
        }

        sb.append(viewPath);

        if (parameters.size() > 0) {
            sb.append("?");

            boolean isFirst = true;
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                String parameter = entry.getKey();
                String value = entry.getValue();

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
