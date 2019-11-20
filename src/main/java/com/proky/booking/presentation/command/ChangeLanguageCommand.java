package com.proky.booking.presentation.command;

import com.proky.booking.util.URLBuilder;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.constans.http.Parameters;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.proky.booking.util.properties.ViewProperties.INDEX;

public class ChangeLanguageCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(ChangeLanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final String chosenLanguage = request.getParameter(Parameters.LANGUAGE);
        final HttpSession session = request.getSession();
        session.setAttribute(Attributes.LANGUAGE, chosenLanguage);

        String uri = request.getRequestURI();
        log.info("uri: {}", uri);
        String referer = request.getHeader("referer");
        log.info("referer: {}", referer);

        return new URLBuilder(true, ViewProperties.getValue(INDEX)).buildURL();
    }
}

