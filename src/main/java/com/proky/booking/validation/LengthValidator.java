package com.proky.booking.validation;


import com.proky.booking.util.properties.MessageProperties;
import com.proky.booking.validation.annotation.Length;

import java.lang.reflect.Field;

import static com.proky.booking.util.properties.MessageProperties.CANT_ACCESS_FIELD;
import static com.proky.booking.util.properties.MessageProperties.LENGTH_VALIDATION;

public class LengthValidator extends Validator {

    @Override
    public boolean validate(Field field, Object validatedObject) {
        field.setAccessible(true);

        final Length declaredAnnotation = field.getDeclaredAnnotation(Length.class);
        final int min = declaredAnnotation.min();
        final int max = declaredAnnotation.max();

        try {
            final Object value =  field.get(validatedObject);

            boolean notValid = false;
            if (value instanceof String) {
                int length = ((String) value).length();
                notValid = (length < min && length > max);
            }

            if (notValid) {
                validatedField = field.getName();
                errorMessage = MessageProperties.getMessage(LENGTH_VALIDATION);
            }

            return notValid;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(MessageProperties.getMessage(CANT_ACCESS_FIELD));
        }
    }
}
