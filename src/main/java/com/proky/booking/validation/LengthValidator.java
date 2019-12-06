package com.proky.booking.validation;


import com.proky.booking.validation.annotation.Length;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.reflect.Field;

public class LengthValidator extends Validator {
    private static final Logger log = LogManager.getLogger(LengthValidator.class);

    @Override
    public boolean validate(Field field, Object validatedObject) {
        field.setAccessible(true);
        validatedField = field.getName();

        final Length declaredAnnotation = field.getDeclaredAnnotation(Length.class);
        final int min = declaredAnnotation.min();
        final int max = declaredAnnotation.max();


        try {
            final Object value =  field.get(validatedObject);

            boolean notValid = false;
            if (value instanceof String) {
                int length = ((String) value).length();
                notValid = (length < min || length > max);
            }

            if (notValid) {
                message =  new ErrorMessage("error.field.text.length", new Object[]{min, max});
            }

            return notValid;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Can not get access to the "+ validatedField +" value.");
        }
    }

}
