package com.proky.booking.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.function.Predicate;

public class AnnotationChecker {
    public boolean isAnnotationInCLass(Class<?> aClass, Predicate<Annotation> predicate) {
        final Method[] declaredMethods = aClass.getDeclaredMethods();

        boolean isAnnotationPresent = false;

        for (Method method : declaredMethods) {
            final Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            for (Annotation declaredAnnotation : declaredAnnotations) {
                System.out.println(declaredAnnotation.toString());
                isAnnotationPresent = predicate.test(declaredAnnotation);
            }
        }

        return isAnnotationPresent;
    }
}
