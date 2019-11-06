package com.proky.booking.util.command;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Map;

public class HttpRequestDataBinder {
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

    public <T> T bindToEntity(HttpServletRequest request, Class<T> tClass) {
        final Map<String, String[]> parameterMap = request.getParameterMap();

        T result = null;

        try {
            Constructor constructor = tClass.getConstructor();
            final Object object = constructor.newInstance();
            final Field[] fields = tClass.getDeclaredFields();
            System.out.println(fields.length);

            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                for (Field field : fields) {

                    final String parameterName = entry.getKey();
                    final String[] values = entry.getValue();

                    if (field.getName().equals(parameterName)) {
                        field.setAccessible(true);
                        final Object value = values[0];
                        field.set(object, value);
                    }
                }
            }

            result = tClass.cast(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
