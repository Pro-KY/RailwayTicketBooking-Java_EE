package com.proky.booking.exception;

import java.sql.SQLException;

public class DataAccessException extends RuntimeException {
    private int errorCode;
    private String state;

    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
        getCodeAndState(cause);
    }

    private void getCodeAndState(Throwable cause) {
        if (cause instanceof SQLException) {
            errorCode = ((SQLException) cause).getErrorCode();
            state = ((SQLException) cause).getSQLState();
        } else {
            errorCode = -1;
            state = cause.getMessage();
        }
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getState() {
        return state;
    }

    public DataAccessException(Throwable cause) {
        super(cause);
    }

}
