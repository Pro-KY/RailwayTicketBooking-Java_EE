package com.proky.booking.validation;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {
    private boolean isValid = true;
    // field, ErrorMessage
    private Map<String, String> errorMessages = new HashMap<>();


    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public void addErrorMessage(String fieldName, String message) {
        errorMessages.put(fieldName, message);
    }

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }
}
