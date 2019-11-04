package com.proky.booking.presentation.controller;
import com.proky.booking.presentation.command.CommandFactory;
import com.proky.booking.presentation.command.ICommand;
import com.proky.booking.util.constans.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    private static final Logger log = LogManager.getLogger(FrontController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet");
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doPost");
        handleRequest(req, resp);
    }

    private void handleRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final ICommand command = CommandFactory.getCommand(request);
        final String view = command.execute(request);

        if (view == null) {
            response.sendRedirect("/error.jsp");
        } else if (view.startsWith(Command.REDIRECT)) {
            response.sendRedirect(view.replace("redirect:", ""));
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
            dispatcher.forward(request, response);
        }
    }
}
