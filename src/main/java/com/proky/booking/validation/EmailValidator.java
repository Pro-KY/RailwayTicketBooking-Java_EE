package com.proky.booking.validation;


import com.proky.booking.util.properties.MessageProperties;
import com.proky.booking.validation.annotation.Email;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.regex.Pattern;

import static com.proky.booking.util.properties.MessageProperties.*;

public class EmailValidator extends Validator {
    private static final Logger log = LogManager.getLogger(EmailValidator.class);

    @Override
    public boolean validate(Field field, Object validationObject) {
        field.setAccessible(true);

        final Email declaredAnnotation = field.getDeclaredAnnotation(Email.class);
        final String emailRegExpPattern = declaredAnnotation.pattern();

        try {
            final String emailValue = (String) field.get(validationObject);
            boolean notValid = !Pattern.matches(emailRegExpPattern, emailValue);
            log.info("email notValid: {}", notValid);

            if (notValid) {
                validatedField = field.getName();
                errorMessage = MessageFormat.format(MessageProperties.getMessage(EMAIL_VALIDATION), emailValue);
            }

            return notValid;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(MessageProperties.getMessage(CANT_ACCESS_FIELD));
        }
    }
}
