package com.proky.booking.validation;


import com.proky.booking.exception.ValidatonException;

import java.lang.reflect.Field;

public class NullValidator implements Validator {
    private String errorMsg = "value is null";

    @Override
    public void validate(Field field, Object validationObject) {
        try {
            field.setAccessible(true);
            final Object fieldValue = field.get(validationObject);
            boolean notValid = !(fieldValue == null);
            if (notValid) {
                throw new ValidatonException(errorMsg);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException();
        }
    }
}
