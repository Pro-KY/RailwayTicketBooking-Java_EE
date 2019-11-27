package com.proky.booking.exception;

import com.proky.booking.dto.ErrorData;
import com.proky.booking.util.UrlBuilder;
import com.proky.booking.util.constans.http.Attributes;
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
        ErrorData errorData = new ErrorData(request);
        final String exceptionMessage = errorData.getExceptionMessage();

        String currentPage = request.getHeader("referer");
        final UrlBuilder urlBuilder = new UrlBuilder(currentPage);

        if(exceptionMessage != null && exceptionMessage.startsWith(SERVICE_EXCEPTION.name)) {
            log.debug("handle service exception name");
            errorData.setExceptionMessage(exceptionMessage.replace(SERVICE_EXCEPTION.name, ""));
            urlBuilder.setAlertParameters(false, errorData.getExceptionMessage());
            response.sendRedirect(urlBuilder.buildURL());
        } else {
            log.debug("handle runtime error");
            request.setAttribute(Attributes.ERROR_REQUEST_URI, errorData.getRequestURI());
            request.setAttribute(Attributes.ERROR_SERVLET_NAME, errorData.getServletName());
            request.setAttribute(Attributes.ERROR_STATUS_CODE, errorData.getStatusCode());
            request.setAttribute(Attributes.ERROR_EXCEPTION_NAME, errorData.getExceptionName());
            request.setAttribute(Attributes.ERROR_EXCEPTION_MSG, errorData.getExceptionMessage());
            request.getRequestDispatcher(ViewProperties.getValue(ERROR_RUNTIME)).forward(request,response);
        }
    }
}
