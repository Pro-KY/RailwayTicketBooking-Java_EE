package com.proky.booking.util;
import java.util.Map;

import static com.proky.booking.util.constans.Commands.REDIRECT;

public class URLBuilder {
    private StringBuilder sb = new StringBuilder();
    private String viewPath;
    private Map<String, String> parameters;
    private boolean redirect;

    public URLBuilder(String viewPath) {
        this.viewPath = viewPath;
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

    public String buildURL() {
        if (redirect) {
            sb.append(REDIRECT);
        }

        sb.append(viewPath);

        if (parameters != null) {
            sb.append("?");

            parameters.forEach((parameter,value) -> sb.append(parameter).append("=").append(value));
        }

        return sb.toString();
    }
}
