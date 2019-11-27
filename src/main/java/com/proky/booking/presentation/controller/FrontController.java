package com.proky.booking.presentation.controller;
import com.proky.booking.presentation.command.CommandFactory;
import com.proky.booking.presentation.command.ICommand;
import com.proky.booking.util.constans.http.Commands;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.proky.booking.util.properties.ViewProperties.ERROR_RUNTIME;

public class FrontController extends HttpServlet {
    private static final Logger log = LogManager.getLogger(FrontController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("doGet");
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("doPost");
        handleRequest(req, resp);
    }

    private void handleRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final ICommand command = CommandFactory.getCommand(request);

        final String viewPath = command.execute(request);

        if (viewPath == null) {
            response.sendRedirect(ViewProperties.getValue(ERROR_RUNTIME));
        } else if (viewPath.startsWith(Commands.REDIRECT)) {
            log.debug("REDIRECT!!!");
            response.sendRedirect(viewPath.replace(Commands.REDIRECT, ""));
        } else {
            log.debug("FORWARD!!!");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(viewPath);
            dispatcher.forward(request, response);
        }
    }
}
