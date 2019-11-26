package com.proky.booking.presentation.command.databinder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Map;

public class HttpRequestDataBinder {
    private static final Logger log = LogManager.getLogger(HttpRequestDataBinder.class);

    private static HttpRequestDataBinder mInstance;

    private HttpRequestDataBinder() {}

    public static HttpRequestDataBinder getInstance() {
        if (mInstance == null) {
            mInstance = new HttpRequestDataBinder();
        }
        return mInstance;
    }

    public <T> T bindToDto(HttpServletRequest request, Class<T> tClass) {
        return bindToEntity(request, tClass);
    }

    private void bindParametersToFields(Field[] fields, Map<String, String[]> parameterMap, Object object) throws IllegalAccessException {
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            for (Field field : fields) {

                final String parameterName = entry.getKey();

                if (field.getName().equals(parameterName)) {
                    field.setAccessible(true);
                    final Object value = entry.getValue()[0];
                    field.set(object, value);
                    break;
                }
            }
        }
    }

    public <T> T bindToEntity(HttpServletRequest request, Class<T> tClass) {
        final Map<String, String[]> parameterMap = request.getParameterMap();

        T result = null;

        try {
            Constructor constructor = tClass.getConstructor();
            final Object object = constructor.newInstance();
            final Field[] fields = tClass.getDeclaredFields();
            log.debug("fields amount, {}", fields.length);

            bindParametersToFields(fields, parameterMap, object);

//            final Class<? super T> superclass = tClass.getSuperclass();
            final Field[] superClassFields = tClass.getSuperclass().getDeclaredFields();
            if (superClassFields.length > 0) {
                log.debug("superClass fields length, {}", superClassFields.length);
                bindParametersToFields(superClassFields, parameterMap, object);
            }

            result = tClass.cast(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
