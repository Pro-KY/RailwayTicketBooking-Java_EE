package com.proky.booking.validation;


import com.proky.booking.annotation.Email;
import com.proky.booking.exception.ValidatonException;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class EmailValidator implements Validator {
    private String errorMsg = "invalid email value";

    @Override
    public void validate(Field field, Object validationObject) {
        field.setAccessible(true);

        final Email declaredAnnotation = field.getDeclaredAnnotation(Email.class);
        final String emailPattern = declaredAnnotation.pattern();
        System.out.println(emailPattern);

        try {
            final String emailValue = (String) field.get(validationObject);
            boolean notValid = !Pattern.matches(emailPattern, emailValue);
            if (notValid) {
                throw new ValidatonException(errorMsg);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("can't access field values");
        }
    }
}
