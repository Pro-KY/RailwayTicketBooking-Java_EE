package com.proky.booking.validation;
import com.proky.booking.validation.annotation.Size;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.reflect.Field;

public class SizeValidator extends Validator {
    private static final Logger log = LogManager.getLogger(SizeValidator.class);

    @Override
    public boolean validate(Field field, Object validatedObject) {
        field.setAccessible(true);
        validatedField = field.getName();

        final Size declaredAnnotation = field.getDeclaredAnnotation(Size.class);
        final int min = declaredAnnotation.min();
        final int max = declaredAnnotation.max();

        try {
            final Object value =  field.get(validatedObject);

            boolean notValid = false;
            if (value instanceof Number) {
                Number number = (Number) value;
                notValid = (number.intValue() < min || number.intValue() > max);
            }

            if (notValid) {
                message =  new ErrorMessage("error.field.number.size", new Object[]{min, max});
            }
            return notValid;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Can not get access to the " + validatedField + " value.");
        }
    }
}
