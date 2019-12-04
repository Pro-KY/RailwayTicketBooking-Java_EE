package com.proky.booking.validation;


import com.proky.booking.util.properties.MessageProperties;
import com.proky.booking.validation.annotation.Size;

import java.lang.reflect.Field;

import static com.proky.booking.util.properties.MessageProperties.*;

public class SizeValidator extends Validator {

    @Override
    public boolean validate(Field field, Object validatedObject) {
        field.setAccessible(true);

        final Size declaredAnnotation = field.getDeclaredAnnotation(Size.class);
        final int min = declaredAnnotation.min();
        final int max = declaredAnnotation.max();

        try {
            final Object value =  field.get(validatedObject);

            boolean notValid = false;
            if (value instanceof Number) {
                Number number = (Number) value;
                notValid = (number.intValue() < min && number.intValue() > max);
            }

            if (notValid) {
                validatedField = field.getName();
                errorMessage = MessageProperties.getMessage(SIZE_VALIDATION);
            }

            return notValid;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(MessageProperties.getMessage(CANT_ACCESS_FIELD));
        }
    }
}
