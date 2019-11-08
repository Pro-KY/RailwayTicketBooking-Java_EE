package com.proky.booking.presentation.command.fragment;

import com.proky.booking.presentation.command.ICommand;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.properties.ViewProperties;

import javax.servlet.http.HttpServletRequest;

import static com.proky.booking.util.properties.ViewProperties.FRAGMENT_SIGN_UP;
import static com.proky.booking.util.properties.ViewProperties.INDEX;

public class SignUpFragmentCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(Attributes.SIGN_UP_FRAGMENT, ViewProperties.getPath(FRAGMENT_SIGN_UP));
        return ViewProperties.getPath(INDEX);
    }
}
