package com.proky.booking.presentation.command.user;

import com.proky.booking.dto.UserDto;
import com.proky.booking.persistence.entity.User;
import com.proky.booking.presentation.command.CommandUtil;
import com.proky.booking.util.HttpFormBinder;
import com.proky.booking.presentation.command.ICommand;
import com.proky.booking.service.*;
import com.proky.booking.util.UrlBuilder;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.constans.UserTypeEnum;
import com.proky.booking.util.constans.http.Commands;
import com.proky.booking.validation.ValidationResult;
import com.proky.booking.validation.ValidationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class SignInCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(SignInCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();

        UrlBuilder urlBuilder = new UrlBuilder(true);
        final UserService userService = ServiceFactory.getInstance().getUserService();

        final HttpFormBinder requestDataBinder = HttpFormBinder.getInstance();
        final UserDto enteredUserCredentials = requestDataBinder.bindToDto(request, UserDto.class);

        final ValidationService validationService = ValidationService.getInstance();
        final ValidationResult validation = validationService.validate(enteredUserCredentials, "email", "password");

        if (validation.isSuccessfull()) {
            final User authenticatedUser = userService.signIn(enteredUserCredentials);
            final boolean isAdministrator = userService.isAdministrator(authenticatedUser);
            final UserDto userDto = userService.getUserDtoByUser(authenticatedUser);
            UserTypeEnum userTypeEnum = isAdministrator ? UserTypeEnum.ADMIN : UserTypeEnum.USER;

            String command = isAdministrator ? Commands.ALL_USERS : Commands.HOME;
            urlBuilder.setAttribute(Attributes.COMMAND, command);
            urlBuilder.setContextPath(request.getContextPath());

            session.setAttribute(Attributes.USER_TYPE, userTypeEnum);
            session.setAttribute(Attributes.USER, userDto);
        } else {
            urlBuilder.setViewPath(CommandUtil.getReferer(request));
            CommandUtil.setValidationResultToSession(session, validation);
        }

        return urlBuilder.buildURL();
    }
}
