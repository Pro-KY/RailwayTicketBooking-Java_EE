package com.proky.booking.exception;

import com.proky.booking.dto.ErrorData;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.proky.booking.util.constans.ExceptionsEnum.SERVICE_EXCEPTION;
import static com.proky.booking.util.properties.ViewProperties.ERROR_RUNTIME;

public class GlobalExceptionHandler extends HttpServlet {
    private static final Logger log = LogManager.getLogger(GlobalExceptionHandler.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        handleError(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        handleError(request, response);
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("handle error data");
        ErrorData errorData = new ErrorData(request);
        final String exceptionMessage = errorData.getExceptionMessage();

        String currentPage = (String)request.getSession().getAttribute(Attributes.CURRENT_PAGE);

        if(exceptionMessage != null && exceptionMessage.startsWith(SERVICE_EXCEPTION.name)) {
            log.debug("handle {}", SERVICE_EXCEPTION.name);
            errorData.setExceptionMessage(exceptionMessage.replace(SERVICE_EXCEPTION.name, ""));

            request.setAttribute(Attributes.ALERT_ERROR, true);
            request.setAttribute(Attributes.ALERT_MESSAGE, errorData.getExceptionMessage());
            request.getRequestDispatcher(currentPage).forward(request, response);
        } else {
            request.setAttribute(Attributes.ERROR_REQUEST_URI, errorData.getRequestURI());
            request.setAttribute(Attributes.ERROR_SERVLET_NAME, errorData.getServletName());
            request.setAttribute(Attributes.ERROR_STATUS_CODE, errorData.getStatusCode());
            request.setAttribute(Attributes.ERROR_EXCEPTION_NAME, errorData.getExceptionName());
            request.setAttribute(Attributes.ERROR_EXCEPTION_MSG, errorData.getExceptionMessage());
            currentPage = ViewProperties.getValue(ERROR_RUNTIME);
        }

        request.getRequestDispatcher(currentPage).forward(request, response);
    }
}
