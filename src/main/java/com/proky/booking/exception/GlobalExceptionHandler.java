package com.proky.booking.exception;

import com.proky.booking.dto.ErrorDto;
import com.proky.booking.presentation.command.CommandUtil;
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

import static com.proky.booking.util.properties.ViewProperties.ERROR_RUNTIME;

/**
 * The class is a global exception handler for the whole application. It intercepts all kind of exceptions and
 * redirect to the previous page where an error is occured or forward to runtime error page with specific error data.
 */
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

    /**
     * The class is a global exception handler for the whole application. It intercepts all kind of exceptions and
     * redirect to the previous page where an error is occured or forward to runtime error page with specific error data.
     *
     * @param request  @see HttpServletRequest
     * @param response  route identifier
     */
    private void handleError(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ErrorDto errorDto = new ErrorDto(request);
        final String exceptionMessage = errorDto.getExceptionMessage();

        String currentPage = CommandUtil.getReferer(request);
        final UrlBuilder urlBuilder = new UrlBuilder(currentPage);

        if(exceptionMessage != null && exceptionMessage.startsWith(ServiceException.NAME)) {
            log.debug("handle service exception");
            errorDto.setExceptionMessage(exceptionMessage.replace(ServiceException.NAME, ""));
            CommandUtil.setAlertAttributes(false, errorDto.getExceptionMessage(), request.getSession());
            response.sendRedirect(urlBuilder.buildURL());
        } else {
            log.debug("handle runtime error");
            request.setAttribute(Attributes.ERROR_REQUEST_URI, errorDto.getRequestURI());
            request.setAttribute(Attributes.ERROR_SERVLET_NAME, errorDto.getServletName());
            request.setAttribute(Attributes.ERROR_STATUS_CODE, errorDto.getStatusCode());
            request.setAttribute(Attributes.ERROR_EXCEPTION_NAME, errorDto.getExceptionName());
            request.setAttribute(Attributes.ERROR_EXCEPTION_MSG, errorDto.getExceptionMessage());
            request.getRequestDispatcher(ViewProperties.getValue(ERROR_RUNTIME)).forward(request,response);
        }
    }
}
