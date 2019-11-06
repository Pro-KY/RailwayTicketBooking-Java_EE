package com.proky.booking.validation;

import java.lang.reflect.Field;

public interface Validator {
    void validate(Field field, Object validationObject);
}
