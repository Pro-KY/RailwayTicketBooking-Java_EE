package com.proky.booking.validation;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {
    private boolean isSuccessfull = true;
    // field, ErrorMessage
    private Map<String, String> errorMessages = new HashMap<>();


    public boolean isSuccessfull() {
        return isSuccessfull;
    }

    public void setSuccessfull(boolean successfull) {
        this.isSuccessfull = successfull;
    }

    public void addErrorMessage(String fieldName, String message) {
        errorMessages.put(fieldName, message);
    }

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }
}
