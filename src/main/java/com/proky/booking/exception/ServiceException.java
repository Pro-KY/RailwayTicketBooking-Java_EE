package com.proky.booking.exception;

/**
 * The class represents custom exception thrown from the Service layer with message describing the cause.
 */
public class ServiceException extends RuntimeException {
    public static final String NAME = "ServiceException";

    public ServiceException() {}

    public ServiceException(String message) {
        super(NAME + message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
