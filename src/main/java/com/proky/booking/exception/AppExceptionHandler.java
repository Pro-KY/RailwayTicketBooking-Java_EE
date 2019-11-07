package com.proky.booking.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.proky.booking.util.constans.Exceptions.SERVICE_EXCEPTION;

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
        log.info("Here!!");

        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");

        String msg = "default";

        final String message = throwable.getMessage();
        if(message.startsWith(SERVICE_EXCEPTION.name)) {
            log.info(SERVICE_EXCEPTION.name);
            msg = message.replace(SERVICE_EXCEPTION.name, "");
        } else {
            response.sendRedirect("/jsp/error");
        }

        log.info(request.getContextPath());
        String url = request.getContextPath() + "?errMsg=" + msg;
        response.sendRedirect(url);
    }
}
