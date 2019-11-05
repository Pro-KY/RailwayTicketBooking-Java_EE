package com.proky.booking.presentation.command.fragment;

import com.proky.booking.presentation.command.ICommand;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.properties.ViewPath;

import javax.servlet.http.HttpServletRequest;

public class SignUpFragmentCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(Attributes.SIGN_UP_FRAGMENT, ViewPath.FRAGMENT_SIGN_UP);
        return ViewPath.INDEX;
    }
}
