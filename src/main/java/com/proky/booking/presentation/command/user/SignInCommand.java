package com.proky.booking.presentation.command.user;

import com.proky.booking.dto.PageDto;
import com.proky.booking.dto.UserDto;
import com.proky.booking.persistence.entity.Station;
import com.proky.booking.persistence.entity.User;
import com.proky.booking.presentation.command.CommandUtil;
import com.proky.booking.util.HttpRequestDataBinder;
import com.proky.booking.presentation.command.ICommand;
import com.proky.booking.service.*;
import com.proky.booking.util.UrlBuilder;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.constans.UserTypeEnum;
import com.proky.booking.util.properties.ViewProperties;
import com.proky.booking.validation.ValidationResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static com.proky.booking.util.properties.ViewProperties.*;


public class SignInCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(SignInCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();

        UrlBuilder urlBuilder = new UrlBuilder(true, ViewProperties.getValue(INDEX));
        final SignInService signInService = ServiceFactory.getInstance().getSignInService();
        final UserService userService = ServiceFactory.getInstance().getUserService();

        log.debug("user signed in");
        final HttpRequestDataBinder requestDataBinder = HttpRequestDataBinder.getInstance();
        final UserDto enteredUserCredentials = requestDataBinder.bindToDto(request, UserDto.class);

        final ValidationService validationService = ValidationService.getInstance();
        final ValidationResult validation = validationService.validate(enteredUserCredentials, "email", "password");

        if (validation.isSuccessfull()) {
            final User authenticatedUser = signInService.signIn(enteredUserCredentials);
            final boolean isAdministrator = userService.isAdministrator(authenticatedUser);
            final UserDto userDto = userService.mapUserToDto(authenticatedUser);
            UserTypeEnum userTypeEnum = isAdministrator ? UserTypeEnum.ADMIN : UserTypeEnum.USER;

            if (isAdministrator) {
                PageDto pageDto = new PageDto();
                final PageDto usersPerPage = userService.findAllRegisteredUsers(pageDto);
                session.setAttribute(Attributes.MODEL, usersPerPage);
                urlBuilder.setViewPath(request.getContextPath() + ViewProperties.getValue(ADMIN_USERS));
            }

            final ServiceFactory serviceFactory = ServiceFactory.getInstance();
            final StationService stationService = serviceFactory.getStationService();
            final List<Station> allStations = stationService.findAllStations();
            session.setAttribute(Attributes.USER_TYPE, userTypeEnum);
            session.setAttribute(Attributes.USER, userDto);
            session.setAttribute(Attributes.STATIONS, allStations);
        } else {
            urlBuilder.setViewPath(CommandUtil.getReferer(request));
            CommandUtil.setValidationResultToSession(session, validation);
            urlBuilder.setAttribute(Attributes.ERROR, true);
        }

        return urlBuilder.buildURL();
    }
}