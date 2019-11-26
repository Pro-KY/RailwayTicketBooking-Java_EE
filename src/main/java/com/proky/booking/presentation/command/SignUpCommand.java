package com.proky.booking.presentation.command;

import com.proky.booking.persistence.entity.User;
import com.proky.booking.presentation.command.databinder.HttpRequestDataBinder;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.service.SignUpService;
import com.proky.booking.service.ValidationService;
import com.proky.booking.util.UrlBuilder;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.MessageProperties;
import com.proky.booking.util.properties.ViewProperties;
import com.proky.booking.validation.ValidationResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.proky.booking.util.properties.MessageProperties.USER_CREATED;
import static com.proky.booking.util.properties.ViewProperties.*;


public class SignUpCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(SignUpCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final String signUpViewpath = ViewProperties.getValue(SIGN_UP);
        final String signInViewpath = ViewProperties.getValue(SIGN_IN);

//        session.setAttribute(Attributes.CURRENT_PAGE, signUpViewpath);
        final UrlBuilder urlBuilder = new UrlBuilder(true, signUpViewpath);

        log.info("user sign up");
        final HttpRequestDataBinder requestDataBinder = HttpRequestDataBinder.getInstance();
        final User user = requestDataBinder.bindToEntity(request, User.class);
        log.info(user);

        final ValidationService validationService = ValidationService.getInstance();
        final ValidationResult validation = validationService.validate(user);

        if (validation.isSuccessfull()) {
            final SignUpService signUpService = ServiceFactory.getInstance().getSignUpService();
            signUpService.signUp(user);

            urlBuilder.setAlertParameters(true, MessageProperties.getMessage(USER_CREATED));
            urlBuilder.setViewPath(signInViewpath);
//            session.setAttribute(Attributes.CURRENT_PAGE, signInViewpath);
        } else {
            urlBuilder.setRedirect(false);
            request.setAttribute(Attributes.ALERT_ERROR, true);
            request.setAttribute(Attributes.VALIDATION, validation);
        }

        return urlBuilder.buildURL();
    }
}
