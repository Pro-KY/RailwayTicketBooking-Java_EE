package com.proky.booking.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.function.Predicate;

/**
 * The class check whether a class methods have required annotations
 */
public class AnnotationChecker {
    private static AnnotationChecker mInstance;

    private AnnotationChecker() {
    }

    public static AnnotationChecker getInstance() {
        if (mInstance == null) {
            mInstance = new AnnotationChecker();
        }
        return mInstance;
    }

    public boolean isAnnotationInCLass(Class<?> aClass, Predicate<Annotation> predicate) {
        final Method[] declaredMethods = aClass.getDeclaredMethods();

        boolean isAnnotationPresent = false;

        for (Method method : declaredMethods) {
            final Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            for (Annotation declaredAnnotation : declaredAnnotations) {
                isAnnotationPresent = predicate.test(declaredAnnotation);
            }
        }

        return isAnnotationPresent;
    }
}
