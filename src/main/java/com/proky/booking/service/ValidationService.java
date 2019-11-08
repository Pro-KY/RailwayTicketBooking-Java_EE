package com.proky.booking.service;

import com.proky.booking.annotation.Email;
import com.proky.booking.annotation.NotNull;
import com.proky.booking.annotation.Text;
import com.proky.booking.validation.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ValidationService {
    private static ValidationService mInstance;

    private ValidationService() {}

    public static ValidationService getInstance() {
        if (mInstance == null) {
            mInstance = new ValidationService();
        }
        return mInstance;
    }

    public <T> ValidationResult validate(T object) {

        final ValidationResult validationResult = new ValidationResult();

        final Class<?> aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            final Annotation[] declaredAnnotations = field.getDeclaredAnnotations(); // field might have two or more annotations
            for (Annotation declaredAnnotation : declaredAnnotations) {
                final Validator validator = getValidator(declaredAnnotation);

                final boolean isInvalid = validator.validate(field, object);
                if (isInvalid) {
                    validationResult.setSuccessfull(false);
                    validationResult.addErrorMessage(validator.getValidatedField(), validator.getErrorMessage());
                    break;
                }
            }
        }

        return validationResult;
    }

    private Validator getValidator(Annotation annotation) {
        Validator validator = null;

        if (annotation instanceof NotNull) {
            validator = new NullValidator();
        } else if (annotation instanceof Number) {
            validator = new NumberValidator();
        } else if (annotation instanceof Text) {
            validator = new TextValidator();
        } else if (annotation instanceof Email) {
            validator = new EmailValidator();
        }
        return validator;
    }
}
