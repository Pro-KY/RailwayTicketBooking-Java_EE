package com.proky.booking.exception;

import com.proky.booking.dto.ErrorData;
import com.proky.booking.util.URLBuilder;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.constans.Parameters;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.proky.booking.util.constans.ExceptionsEnum.SERVICE_EXCEPTION;
import static com.proky.booking.util.properties.ViewProperties.ERROR;

public class AppExceptionHandler extends HttpServlet {
    private static final Logger log = LogManager.getLogger(AppExceptionHandler.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        handleError(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        handleError(request, response);
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("handle error");
        ErrorData errorData = new ErrorData(request);

        final String exceptionMessage = errorData.getExceptionMessage();

        if(exceptionMessage != null && exceptionMessage.startsWith(SERVICE_EXCEPTION.name)) {
            log.info(SERVICE_EXCEPTION.name);
            errorData.setExceptionMessage(exceptionMessage.replace(SERVICE_EXCEPTION.name, ""));
        }

        final URLBuilder urlBuilder = new URLBuilder(false,  ViewProperties.getPath(ERROR));
        urlBuilder.setParameter(Parameters.ERROR_REQUEST_URI, errorData.getRequestURI());
        urlBuilder.setParameter(Parameters.ERROR_SERVLET_NAME, errorData.getServletName());
        urlBuilder.setParameter(Parameters.ERROR_STATUS_CODE, errorData.getStatusCode());
        urlBuilder.setParameter(Parameters.ERROR_EXCEPTION_NAME, errorData.getExceptionName());
        urlBuilder.setParameter(Parameters.ERROR_EXCEPTION_MSG, errorData.getExceptionMessage());

        response.sendRedirect(urlBuilder.buildURL());
    }
}
