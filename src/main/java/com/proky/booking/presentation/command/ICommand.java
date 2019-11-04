package com.proky.booking.presentation.command;

import javax.servlet.http.HttpServletRequest;

public interface ICommand {
    /**
     * Handles an HttpServletRequest and returns rendered jsp page
     *
     * @param request income HttpServletRequest from the user
     * @return view path that will be rendered
     */
    String execute(HttpServletRequest request);
}
