package com.proky.booking.validation;

import com.proky.booking.exception.ValidatonException;

import java.lang.reflect.Field;

public class TextValidator implements Validator {
    private String errorMsg = "invalid text value";

    @Override
    public void validate(Field field, Object validationObject) {
        field.setAccessible(true);
        try {
            //TODO: don't let user numbers in text
            final String text = (String) field.get(validationObject);
            if (text == null || text.isEmpty()) {
                throw new ValidatonException(errorMsg);
            }

        } catch (IllegalAccessException e) {
            throw new RuntimeException("can't access field values");
        }
    }
}
