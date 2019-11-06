package com.proky.booking.util.command;

import javax.servlet.http.HttpServletRequest;

public interface HttpRequestHandler<T> {
    T extractParametersToEntity();
    void setRequestAttributes(HttpServletRequest request, T entity);
}
