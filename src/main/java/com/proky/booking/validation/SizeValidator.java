package com.proky.booking.validation;


import com.proky.booking.util.properties.MessageProperties;
import com.proky.booking.validation.annotation.Size;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.text.MessageFormat;

import static com.proky.booking.util.properties.MessageProperties.*;

public class SizeValidator extends Validator {
    private static final Logger log = LogManager.getLogger(SizeValidator.class);

    @Override
    public boolean validate(Field field, Object validatedObject) {
        field.setAccessible(true);
        log.info("in SizeValidator");

        final Size declaredAnnotation = field.getDeclaredAnnotation(Size.class);
        final int min = declaredAnnotation.min();
        final int max = declaredAnnotation.max();
        log.info("min {}", min);
        log.info("max {}", max);

        try {
            final Object value =  field.get(validatedObject);

            boolean notValid = false;
            if (value instanceof Number) {
                Number number = (Number) value;
                notValid = (number.intValue() < min || number.intValue() > max);
            }

            if (notValid) {
                validatedField = field.getName();
                errorMessage = MessageFormat.format(MessageProperties.getMessage(SIZE_VALIDATION), min, max);
            }

            return notValid;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(MessageProperties.getMessage(CANT_ACCESS_FIELD));
        }
    }
}
