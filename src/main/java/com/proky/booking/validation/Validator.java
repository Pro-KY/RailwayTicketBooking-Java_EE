package com.proky.booking.validation;

import java.lang.reflect.Field;

public abstract class Validator {
    String validatedField;
    ErrorMessage message;

    public abstract boolean validate(Field field, Object validationObject);

    public String getValidatedField() {
        return validatedField;
    }


    public ErrorMessage getErrorMessage() {
        return message;
    }
}
