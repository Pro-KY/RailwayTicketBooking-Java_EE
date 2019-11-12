package com.proky.booking.presentation.command;

import com.proky.booking.persistence.entity.User;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.service.SignUpService;
import com.proky.booking.service.ValidationService;
import com.proky.booking.util.URLBuilder;
import com.proky.booking.util.command.HttpRequestDataBinder;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.constans.Parameters;
import com.proky.booking.util.properties.ViewProperties;
import com.proky.booking.validation.ValidationResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class SignUpCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(SignUpCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final URLBuilder urlBuilder = new URLBuilder(true, ViewProperties.INDEX);

        log.info("user sign up");
        final HttpRequestDataBinder requestDataBinder = HttpRequestDataBinder.getInstance();
        final User user = requestDataBinder.bindToEntity(request, User.class);
        log.info(user);

        final ValidationService validationService = ValidationService.getInstance();
        final ValidationResult validation = validationService.validate(user);

        if (validation.isSuccessfull()) {
            final SignUpService signUpService = ServiceFactory.getInstance().getSignUpService();
            signUpService.signUp(user);
        } else {
            urlBuilder.setAttribute(Parameters.SIGN_UP_FRAGMENT, ViewProperties.FRAGMENT_SIGN_UP);
            session.setAttribute(Attributes.VALIDATION, validation);
        }

        return urlBuilder.buildURL();
    }
}
