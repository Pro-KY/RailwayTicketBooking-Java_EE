package com.proky.booking.validation;


import com.proky.booking.annotation.Email;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class EmailValidator extends Validator {

    @Override
    public boolean validate(Field field, Object validationObject) {
        field.setAccessible(true);

        final Email declaredAnnotation = field.getDeclaredAnnotation(Email.class);
        final String emailRegExpPattern = declaredAnnotation.pattern();
        System.out.println(emailRegExpPattern);

        try {
            final String emailValue = (String) field.get(validationObject);
            boolean notValid = !Pattern.matches(emailRegExpPattern, emailValue);

            if (notValid) {
                validatedField = field.getName();
                errorMessage = "invalid email value";
            }

            return notValid;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("can't access field values");
        }
    }
}
