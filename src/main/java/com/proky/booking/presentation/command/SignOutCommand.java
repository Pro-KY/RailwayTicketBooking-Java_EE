package com.proky.booking.presentation.command;

import com.proky.booking.util.URLBuilder;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.proky.booking.util.properties.ViewProperties.INDEX;


public class SignOutCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(SignOutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return new URLBuilder(true, ViewProperties.getValue(INDEX)).buildURL();
    }
}
