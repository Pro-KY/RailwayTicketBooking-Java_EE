package com.proky.booking.presentation.command;

import com.proky.booking.util.URLBuilder;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.constans.Parameters;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.proky.booking.util.properties.ViewProperties.INDEX;
import static com.proky.booking.util.properties.ViewProperties.SIGN_IN;

public class ChangeLanguageCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(ChangeLanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final String chosenLanguage = request.getParameter(Parameters.LANGUAGE);
        final HttpSession session = request.getSession();
        session.setAttribute(Attributes.LANGUAGE, chosenLanguage);
        return new URLBuilder(true, ViewProperties.getPath(INDEX)).buildURL();
    }
}

