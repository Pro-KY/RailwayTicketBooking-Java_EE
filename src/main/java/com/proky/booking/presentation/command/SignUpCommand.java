package com.proky.booking.presentation.command;

import com.proky.booking.persistence.entity.User;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.service.SignUpService;
import com.proky.booking.service.ValidationService;
import com.proky.booking.util.URLBuilder;
import com.proky.booking.util.command.HttpRequestDataBinder;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.constans.Commands;
import com.proky.booking.util.properties.ViewProperties;
import com.proky.booking.validation.ValidationResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.proky.booking.util.constans.Commands.REDIRECT;

public class SignUpCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(SignUpCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();

        log.info("user sign up");
        final HttpRequestDataBinder requestDataBinder = HttpRequestDataBinder.getInstance();
        final User user = requestDataBinder.bindToEntity(request, User.class);
        System.out.println(user);

        // validate
        final ValidationService validationService = ValidationService.getInstance();
        final ValidationResult validation = validationService.validate(user);

        if (validation.isSuccessfull()) {
            final SignUpService signUpService = ServiceFactory.getInstance().getSignUpService();
            signUpService.signUp(user);
        } else {
            session.setAttribute(Attributes.VALIDATION, validation);
        }

        final URLBuilder urlBuilder = new URLBuilder(true, ViewProperties.INDEX);
        return urlBuilder.buildURL();
    }
}
