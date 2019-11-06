package com.proky.booking.validation;


import com.proky.booking.annotation.Number;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class NumberValidator extends Validator {

    @Override
    public boolean validate(Field field, Object validationObject) {
        field.setAccessible(true);

        final Number declaredAnnotation = field.getDeclaredAnnotation(Number.class);
        final String regExpPattern = declaredAnnotation.pattern();

        try {
            final String numberValue = (String) field.get(validationObject);
            boolean notValid = !Pattern.matches(regExpPattern, numberValue);

            if (notValid) {
                validatedField = field.getName();
                errorMessage = "entered value is not a number";
            }

            return notValid;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("can't access field values");
        }
    }
}
