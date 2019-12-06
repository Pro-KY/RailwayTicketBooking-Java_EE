package com.proky.booking.presentation.command;

import com.proky.booking.util.UrlBuilder;
import com.proky.booking.util.constans.UserTypeEnum;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.constans.http.Commands;
import com.proky.booking.util.constans.http.Parameters;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;

import static com.proky.booking.util.properties.ViewProperties.INDEX;

public class LanguageCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(LanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final String chosenLanguage = request.getParameter(Parameters.LANGUAGE);
        final String defaultLanguage = "en";
        final UrlBuilder urlBuilder = new UrlBuilder(true, request.getContextPath());

        String language = (chosenLanguage != null) ? chosenLanguage : defaultLanguage;
        final HttpSession session = request.getSession();
        final UserTypeEnum currentUserType = CommandUtil.getCurrentUserType(session);

        boolean isAdministrator = currentUserType.equals(UserTypeEnum.ADMIN);
        String command = isAdministrator ? Commands.ALL_USERS : Commands.HOME;
        urlBuilder.setAttribute(Attributes.COMMAND, command);

        session.setAttribute(Attributes.LANGUAGE, language);
        return urlBuilder.buildURL();
    }
}

