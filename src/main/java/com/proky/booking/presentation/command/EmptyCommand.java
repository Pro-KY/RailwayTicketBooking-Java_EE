package com.proky.booking.presentation.command;

import com.proky.booking.util.properties.ViewProperties;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        return ViewProperties.INDEX;
    }
}
