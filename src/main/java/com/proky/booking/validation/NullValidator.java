package com.proky.booking.validation;


import java.lang.reflect.Field;

public class NullValidator extends Validator {

    @Override
    public boolean validate(Field field, Object validationObject) {
        try {
            field.setAccessible(true);
            final Object fieldValue = field.get(validationObject);
            final boolean notValid = fieldValue == null;

            if (notValid) {
                validatedField = field.getName();
                errorMessage = "value is null";
            }

            return notValid;
        } catch (IllegalAccessException e) {
            throw new RuntimeException();
        }
    }
}
