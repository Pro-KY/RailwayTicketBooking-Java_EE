package com.proky.booking.presentation.command;

import com.proky.booking.persistence.entity.User;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.service.SignInService;
import com.proky.booking.service.ValidationService;
import com.proky.booking.util.URLBuilder;
import com.proky.booking.util.command.HttpRequestDataBinder;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.properties.MessageProperties;
import com.proky.booking.util.properties.ViewProperties;
import com.proky.booking.validation.ValidationResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.proky.booking.util.properties.MessageProperties.AUTHORIZATION_ERROR;
import static com.proky.booking.util.properties.ViewProperties.INDEX;


public class SignInCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(SignInCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();

        final String indexViewPath = ViewProperties.getPath(INDEX);
        URLBuilder urlBuilder = new URLBuilder(true, indexViewPath);

        log.info("user sign in");
        final HttpRequestDataBinder requestDataBinder = HttpRequestDataBinder.getInstance();
        final User user = requestDataBinder.bindToEntity(request, User.class);
        log.info(user);

        final ValidationService validationService = ValidationService.getInstance();
        final ValidationResult validation = validationService.validate(user, "email", "password");

        if (validation.isSuccessfull()) {
            final SignInService signInService = ServiceFactory.getInstance().getSignInService();
            final User authenticatedUser = signInService.signIn(user);

            session.setAttribute(Attributes.IS_USER_AUTHORIZED, true);
            session.setAttribute(Attributes.USER, authenticatedUser);

        } else {
            request.setAttribute(Attributes.ALERT_ERROR, true);
            request.setAttribute(Attributes.ALERT_MESSAGE, MessageProperties.getMessage(AUTHORIZATION_ERROR));
            urlBuilder = new URLBuilder(false, indexViewPath);
        }

        return urlBuilder.buildURL();
    }
}
