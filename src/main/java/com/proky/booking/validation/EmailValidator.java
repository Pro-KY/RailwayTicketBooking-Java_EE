package com.proky.booking.validation;


import com.proky.booking.validation.annotation.Email;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class EmailValidator extends Validator {
    private static final Logger log = LogManager.getLogger(EmailValidator.class);

    @Override
    public boolean validate(Field field, Object validationObject) {
        field.setAccessible(true);
        validatedField = field.getName();

        final Email declaredAnnotation = field.getDeclaredAnnotation(Email.class);
        final String emailRegExpPattern = declaredAnnotation.pattern();

        try {
            final String emailValue = (String) field.get(validationObject);
            boolean notValid = !Pattern.matches(emailRegExpPattern, emailValue);

            if (notValid) {
                message =  new ErrorMessage("error.email");
            }

            return notValid;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Can not get access to the "+ validatedField +" value.");
        }
    }

}
