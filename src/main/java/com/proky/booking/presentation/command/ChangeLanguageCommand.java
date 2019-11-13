package com.proky.booking.presentation.command;

import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.constans.Parameters;
import com.proky.booking.util.properties.ViewProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.proky.booking.util.properties.ViewProperties.INDEX;


public class ChangeLanguageCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        final String chosenLanguage = request.getParameter(Parameters.LANGUAGE);
        final HttpSession session = request.getSession(true);
        session.setAttribute(Attributes.LANGUAGE, chosenLanguage);
        return ViewProperties.getPath(INDEX);
    }
}

