package com.proky.booking.exception;

/**
 * The class represents custom exception thrown only from the @{code TransactionalProxy}. Used to indicate
 * the place where problems with transaction occur.
 * @see com.proky.booking.persistence.transaction.TransactionalProxy
 */
public class TransactionException extends RuntimeException {
    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
