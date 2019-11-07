package com.proky.booking.exception;
import static com.proky.booking.util.constans.ExceptionsEnum.SERVICE_EXCEPTION;

public class ServiceException extends RuntimeException {

    public ServiceException() {}

    public ServiceException(String message) {
        super(SERVICE_EXCEPTION + message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
