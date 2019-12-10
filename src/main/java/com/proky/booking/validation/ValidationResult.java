package com.proky.booking.validation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ValidationResult {
    private boolean isSuccessfull = true;
    private Map<String, ErrorMessage> errorMessages = new HashMap<>();

    public boolean isSuccessfull() {
        return isSuccessfull;
    }

    public void setSuccessfull(boolean successfull) {
        this.isSuccessfull = successfull;
    }


    public void addErrorMessage(String fieldName, ErrorMessage message) {
        errorMessages.put(fieldName, message);
    }

    public Map<String, ErrorMessage> getErrorMessages() {
        return errorMessages;
    }
}
