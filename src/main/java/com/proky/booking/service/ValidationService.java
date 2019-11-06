package com.proky.booking.service;

import com.proky.booking.annotation.Email;
import com.proky.booking.annotation.NotNull;
import com.proky.booking.annotation.Text;
import com.proky.booking.validation.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ValidationService {
    private static ValidationService mInstance;

    private ValidationService() {
    }

    public static ValidationService getInstance() {
        if (mInstance == null) {
            mInstance = new ValidationService();
        }
        return mInstance;
    }

    public <T> void validate(T object) {

        final Class<?> aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            // field might have two or more annotations
            final Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
            for (Annotation declaredAnnotation : declaredAnnotations) {
                final Validator validator = getValidator(declaredAnnotation);
                validator.validate(field, object);
                // break
            }
        }
    }

    private Validator getValidator(Annotation annotation) {
        Validator validator = null;

        if (annotation instanceof NotNull) {
            System.out.println("is null");
            validator = new NullValidator();
        } else if (annotation instanceof Number) {
            System.out.println("number");
            validator = new NumberValidator();
        } else if (annotation instanceof Text) {
            System.out.println("text");
            validator = new TextValidator();
        } else if (annotation instanceof Email) {
            System.out.println("email");
            validator = new EmailValidator();
        }
        return validator;
    }

}
