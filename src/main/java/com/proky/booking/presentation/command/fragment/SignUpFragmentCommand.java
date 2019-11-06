package com.proky.booking.presentation.command.fragment;

import com.proky.booking.presentation.command.ICommand;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.properties.ViewProperties;

import javax.servlet.http.HttpServletRequest;

public class SignUpFragmentCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(Attributes.SIGN_UP_FRAGMENT, ViewProperties.FRAGMENT_SIGN_UP);
        return ViewProperties.INDEX;
    }
}
