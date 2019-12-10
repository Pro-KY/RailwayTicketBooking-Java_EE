package com.proky.booking.presentation.command;

import com.proky.booking.presentation.controller.FrontController;

import javax.servlet.http.HttpServletRequest;

public interface ICommand {
    /**
     * Handles an HttpServletRequest.
     *
     * @param request HttpServletRequest to be handled
     * @return a String containing path to the jsp page. May or may not contain "redirect:" prefix before
     * which indicates how response should be handled by {@code FrontController}.
     * (i.e "redirect:" - redirect request, otherwise - forward)
     * @see FrontController
     */
    String execute(HttpServletRequest request);
}
