package com.proky.booking.validation;


import com.proky.booking.util.properties.MessageProperties;
import com.proky.booking.validation.annotation.Length;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.text.MessageFormat;

import static com.proky.booking.util.properties.MessageProperties.CANT_ACCESS_FIELD;
import static com.proky.booking.util.properties.MessageProperties.LENGTH_VALIDATION;

public class LengthValidator extends Validator {
    private static final Logger log = LogManager.getLogger(LengthValidator.class);

    @Override
    public boolean validate(Field field, Object validatedObject) {
        field.setAccessible(true);
        log.info("in LengthValidator");

        final Length declaredAnnotation = field.getDeclaredAnnotation(Length.class);
        final int min = declaredAnnotation.min();
        final int max = declaredAnnotation.max();

        log.info("min {}", min);
        log.info("max {}", max);

        try {
            final Object value =  field.get(validatedObject);

            boolean notValid = false;
            if (value instanceof String) {
                int length = ((String) value).length();
                notValid = (length < min || length > max);
            }

            log.info("length notvalid {}", notValid);

            if (notValid) {
                validatedField = field.getName();
                errorMessage = MessageFormat.format(MessageProperties.getMessage(LENGTH_VALIDATION), min, max);
            }

            return notValid;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(MessageProperties.getMessage(CANT_ACCESS_FIELD));
        }
    }
}
