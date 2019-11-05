package com.proky.booking.presentation.command;

import javax.servlet.http.HttpServletRequest;

public interface HttpRequestHandler<T> {
    T extractParametersToEntity();
    void setRequestAttributes(HttpServletRequest request, T entity);
}
