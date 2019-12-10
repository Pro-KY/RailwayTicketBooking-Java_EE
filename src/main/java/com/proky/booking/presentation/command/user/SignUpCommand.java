package com.proky.booking.presentation.command.user;

import com.proky.booking.dto.UserDto;
import com.proky.booking.presentation.command.CommandUtil;
import com.proky.booking.service.UserService;
import com.proky.booking.util.HttpFormBinder;
import com.proky.booking.presentation.command.ICommand;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.validation.ValidationService;
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
        final UrlBuilder urlBuilder = new UrlBuilder(true, request.getContextPath(), ViewProperties.getValue(SIGN_UP));

        final HttpFormBinder requestDataBinder = HttpFormBinder.getInstance();
        final UserDto user = requestDataBinder.bindToDto(request, UserDto.class);

        final ValidationService validationService = ValidationService.getInstance();
        final ValidationResult validation = validationService.validate(user);

        if (validation.isSuccessfull()) {
            final UserService userService = ServiceFactory.getInstance().getUserService();
            userService.signUp(user);
            CommandUtil.setAlertAttributes(true, "alert.user.created", session);
            urlBuilder.setViewPath(ViewProperties.getValue(SIGN_IN));
        } else {
            urlBuilder.setViewPath(CommandUtil.getReferer(request));
            CommandUtil.setValidationResultToSession(session, validation);
        }

        return urlBuilder.buildURL();
    }
}
