package com.proky.booking.validation;

import com.proky.booking.annotation.Text;
import com.proky.booking.presentation.command.SignUpCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class TextValidator extends Validator {
    private static final Logger log = LogManager.getLogger(TextValidator.class);


    @Override
    public boolean validate(Field field, Object validationObject) {
        field.setAccessible(true);

        final Text declaredAnnotation = field.getDeclaredAnnotation(Text.class);
        final String textRegExpPattern = declaredAnnotation.pattern();

        try {
            final String text = (String) field.get(validationObject);
            log.info("text - {}", text);
            boolean notValid = text.isEmpty() || !Pattern.matches(textRegExpPattern, text);

            if (notValid) {
                validatedField = field.getName();
                log.debug("validatedField - {}", validatedField);
                errorMessage = "Text should not be empty and only consist of letters!";
            }

            return notValid;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("can't access field values");
        }
    }
}
