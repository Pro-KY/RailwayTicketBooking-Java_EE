package com.proky.booking.service;

import com.proky.booking.validation.annotation.Email;
import com.proky.booking.validation.*;
import com.proky.booking.validation.annotation.Length;
import com.proky.booking.validation.annotation.Size;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
        return validate(object, new String[] {});
    }

    private <T> ValidationResult validateSpecific(Field[] fields, String[] requiredFields, T object) {
        final ValidationResult validationResult = new ValidationResult();
        Set<String> requiredFieldsSet = new HashSet<>(Arrays.asList(requiredFields));

        for (Field field : fields) {
            final String fieldName = field.getName();

            if (requiredFieldsSet.contains(fieldName)) {
                validateSingleField(validationResult, field, object);
            }
        }
        return validationResult;
    }

    private <T> void validateSingleField(ValidationResult validationResult, Field field, T object) {
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

    private <T> ValidationResult validateAll(Field[] fields, T object) {
        final ValidationResult validationResult = new ValidationResult();
        for (Field field : fields) {
            validateSingleField(validationResult, field, object);
        }
        return validationResult;
    }

    public <T> ValidationResult validate(T object, String...requiredFields) {
        final Class<?> aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();

        return (requiredFields.length == 0) ? validateAll(fields, object) : validateSpecific(fields, requiredFields, object);
    }

    private Validator getValidator(Annotation annotation) {
        Validator validator = null;

        if (annotation instanceof Length) {
            validator = new LengthValidator();
        } else if (annotation instanceof Size) {
            validator = new SizeValidator();
        } else if (annotation instanceof Email) {
            validator = new EmailValidator();
        }
        return validator;
    }
}
