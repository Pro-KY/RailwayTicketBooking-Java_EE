package com.proky.booking.validation;

import java.lang.reflect.Field;

public abstract class Validator {
    String validatedField;
    String errorMessage;

    public abstract boolean validate(Field field, Object validationObject);

    public String getValidatedField() {
        return validatedField;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
