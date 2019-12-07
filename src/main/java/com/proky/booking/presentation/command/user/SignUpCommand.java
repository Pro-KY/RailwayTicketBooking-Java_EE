package com.proky.booking.presentation.command.user;

import com.proky.booking.dto.UserDto;
import com.proky.booking.presentation.command.CommandUtil;
import com.proky.booking.util.HttpFormBinder;
import com.proky.booking.presentation.command.ICommand;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.service.SignUpService;
import com.proky.booking.service.ValidationService;
import com.proky.booking.util.UrlBuilder;
import com.proky.booking.util.properties.ViewProperties;
import com.proky.booking.validation.ValidationResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.proky.booking.util.properties.ViewProperties.*;


public class SignUpCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(SignUpCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final UrlBuilder urlBuilder = new UrlBuilder(true, ViewProperties.getValue(SIGN_UP));

        log.debug("user is signed up");
        final HttpFormBinder requestDataBinder = HttpFormBinder.getInstance();
        final UserDto user = requestDataBinder.bindToDto(request, UserDto.class);

        final ValidationService validationService = ValidationService.getInstance();
        final ValidationResult validation = validationService.validate(user);

        if (validation.isSuccessfull()) {
            final SignUpService signUpService = ServiceFactory.getInstance().getSignUpService();
            signUpService.signUp(user);
            CommandUtil.setAlertAttributes(true, "alert.user.created", session);
            urlBuilder.setViewPath(ViewProperties.getValue(SIGN_IN));
        } else {
            urlBuilder.setViewPath(CommandUtil.getReferer(request));
            CommandUtil.setValidationResultToSession(session, validation);
        }

        return urlBuilder.buildURL();
    }
}
