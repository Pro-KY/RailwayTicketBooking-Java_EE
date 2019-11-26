package com.proky.booking.presentation.command;

import com.proky.booking.dto.PageDto;
import com.proky.booking.dto.UserDto;
import com.proky.booking.presentation.command.databinder.HttpRequestDataBinder;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.service.UserService;
import com.proky.booking.util.UrlBuilder;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.MessageProperties;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.proky.booking.util.properties.MessageProperties.USER_UPDATED;
import static com.proky.booking.util.properties.ViewProperties.ADMIN_USERS;

public class UpdateUserCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(UpdateUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        final HttpSession session = request.getSession();

        final UrlBuilder urlBuilder = new UrlBuilder(true, ViewProperties.getValue(ADMIN_USERS));

        final HttpRequestDataBinder requestDataBinder = HttpRequestDataBinder.getInstance();
        final UserDto user = requestDataBinder.bindToDto(request, UserDto.class);

        final UserService userService = ServiceFactory.getInstance().getUserService();
        userService.updateUser(user);

        urlBuilder.setParameter(Attributes.ALERT_SUCCESS, true);
        urlBuilder.setParameter(Attributes.ALERT_MESSAGE, MessageProperties.getMessage(USER_UPDATED));

        PageDto pageDto = new PageDto();
        final PageDto allRegisteredUsers = userService.findAllRegisteredUsers(pageDto);
        session.setAttribute(Attributes.MODEL, allRegisteredUsers);

        return urlBuilder.buildURL();
    }
}
