package com.proky.booking.presentation.command.fragment;

import com.proky.booking.presentation.command.CommandFactory;
import com.proky.booking.presentation.command.ICommand;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.rmi.log.LogInputStream;

import javax.servlet.http.HttpServletRequest;

import static com.proky.booking.util.properties.ViewProperties.FRAGMENT_SIGN_IN;
import static com.proky.booking.util.properties.ViewProperties.INDEX;

public class SignInFragmentCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(SignInFragmentCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("SignInFragmentCommand CALLED");
        request.setAttribute(Attributes.GET_SIGN_IN_FRAGMENT, ViewProperties.getPath(FRAGMENT_SIGN_IN));
        return ViewProperties.getPath(INDEX);
    }
}
