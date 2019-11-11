package com.proky.booking.dto;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class ErrorData implements Serializable {
    private String requestURI;
    private String servletName;
    private Integer statusCode;
    private String exceptionName;
    private String exceptionMessage;

    public ErrorData() {}

    public ErrorData(String requestURI, String servletName, Integer statusCode, String exceptionName, String exceptionMessage) {
        this.requestURI = requestURI;
        this.servletName = servletName;
        this.statusCode = statusCode;
        this.exceptionName = exceptionName;
        this.exceptionMessage = exceptionMessage;
    }

    public ErrorData(HttpServletRequest request) {
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        exceptionName = throwable.getClass().getName();
        exceptionMessage = throwable.getMessage();

        statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }

        requestURI = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestURI == null) {
            requestURI = "Unknown";
        }
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String toString() {
        return "ErrorData{" +
                "requestURI='" + requestURI + '\'' +
                ", servletName='" + servletName + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", exceptionName='" + exceptionName + '\'' +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                '}';
    }
}
