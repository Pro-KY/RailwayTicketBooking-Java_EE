package com.proky.booking.presentation.command.impl;

import com.proky.booking.presentation.command.ICommand;

import javax.servlet.http.HttpServletRequest;


public class EmptyCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
