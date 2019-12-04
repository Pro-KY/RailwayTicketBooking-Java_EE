package com.proky.booking.presentation.command;

import com.proky.booking.util.UrlBuilder;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.constans.http.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;

public class LanguageCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(LanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final String chosenLanguage = request.getParameter(Parameters.LANGUAGE);
        final String DEFAULT_LANGUAGE = "en";

        String language = (chosenLanguage != null) ? chosenLanguage : DEFAULT_LANGUAGE;
        final HttpSession session = request.getSession();
        session.setAttribute(Attributes.LANGUAGE, language);
        return new UrlBuilder(true, request.getHeader("referer")).buildURL();
    }
}

