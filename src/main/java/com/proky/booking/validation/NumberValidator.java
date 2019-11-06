package com.proky.booking.validation;


import com.proky.booking.exception.ValidatonException;

import java.lang.reflect.Field;

public class NumberValidator  implements Validator {
    private String errorMsg = "is not a number";

    @Override
    public void validate(Field field, Object validationObject) {
        field.setAccessible(true);

        try {
            try {
                final Number fieldValue = (Number) field.get(validationObject);
            } catch (ClassCastException e) {
                throw new ValidatonException(errorMsg);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("can't access field values");
        }

    }
}
