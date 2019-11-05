package com.proky.booking.presentation.command;

import com.proky.booking.presentation.command.ICommand;
import com.proky.booking.util.properties.ViewPath;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        return ViewPath.INDEX;
    }
}
