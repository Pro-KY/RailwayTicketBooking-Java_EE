package com.proky.booking.exception;

public class TransactionException extends RuntimeException {
    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(String message, Object entityId) {
        super(message + entityId);
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
