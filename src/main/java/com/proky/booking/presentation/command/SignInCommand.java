package com.proky.booking.presentation.command;

import com.proky.booking.dto.PageDto;
import com.proky.booking.dto.UserDto;
import com.proky.booking.persistence.entity.User;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.service.SignInService;
import com.proky.booking.service.UserService;
import com.proky.booking.service.ValidationService;
import com.proky.booking.util.URLBuilder;
import com.proky.booking.util.command.HttpRequestDataBinder;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.constans.UserTypeEnum;
import com.proky.booking.util.properties.MessageProperties;
import com.proky.booking.util.properties.ViewProperties;
import com.proky.booking.validation.ValidationResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.proky.booking.util.properties.MessageProperties.AUTHORIZATION_ERROR;
import static com.proky.booking.util.properties.ViewProperties.*;


public class SignInCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(SignInCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();

        URLBuilder urlBuilder = new URLBuilder(true, ViewProperties.getValue(INDEX));
        final SignInService signInService = ServiceFactory.getInstance().getSignInService();
        final UserService userService = ServiceFactory.getInstance().getUserService();

        log.debug("user sign in");
        final HttpRequestDataBinder requestDataBinder = HttpRequestDataBinder.getInstance();
        final UserDto enteredUserData = requestDataBinder.bindToDto(request, UserDto.class);
//        log.debug(enteredUserData);

        session.setAttribute(Attributes.CURRENT_PAGE, ViewProperties.getValue(SIGN_IN));

        final ValidationService validationService = ValidationService.getInstance();
        final ValidationResult validation = validationService.validate(enteredUserData, "email", "password");

        if (validation.isSuccessfull()) {
            final User authenticatedUser = signInService.signIn(enteredUserData);
            final boolean isAdministrator = userService.isAdministrator(authenticatedUser);
            final UserDto userDto = userService.mapUserToDto(authenticatedUser);
            UserTypeEnum userTypeEnum = isAdministrator ? UserTypeEnum.ADMIN : UserTypeEnum.USER;
            log.debug("mapped userDto {}", userDto);

            if (isAdministrator) {
                PageDto pageDto = new PageDto();
                final PageDto usersPerPage = userService.findAllRegisteredUsers(pageDto);
                session.setAttribute(Attributes.MODEL, usersPerPage);
                urlBuilder.setViewPath(ViewProperties.getValue(ADMIN_USERS));
            }

            session.setAttribute(Attributes.USER_TYPE, userTypeEnum);
            session.setAttribute(Attributes.USER, userDto);
//            session.setAttribute(Attributes.IS_USER_AUTHORIZED, true);
        } else {
            request.setAttribute(Attributes.ALERT_ERROR, true);
            request.setAttribute(Attributes.ALERT_MESSAGE, MessageProperties.getMessage(AUTHORIZATION_ERROR));
            urlBuilder.setRedirect(false);
        }

        return urlBuilder.buildURL();
    }
}
