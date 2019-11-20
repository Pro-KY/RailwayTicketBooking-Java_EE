package com.proky.booking.presentation.command;

import com.proky.booking.dto.PageDto;
import com.proky.booking.dto.UserDto;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.service.UserService;
import com.proky.booking.util.URLBuilder;
import com.proky.booking.util.command.HttpRequestDataBinder;
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

        final URLBuilder urlBuilder = new URLBuilder(true, ViewProperties.getValue(ADMIN_USERS));

        log.debug("update user data");
        final HttpRequestDataBinder requestDataBinder = HttpRequestDataBinder.getInstance();
        final UserDto user = requestDataBinder.bindToDto(request, UserDto.class);
        log.debug("mapped form-user: {}", user);

        final UserService userService = ServiceFactory.getInstance().getUserService();
        userService.updateUser(user);


        urlBuilder.setParameter(Attributes.ALERT_SUCCESS, true);
        urlBuilder.setParameter(Attributes.ALERT_MESSAGE, MessageProperties.getMessage(USER_UPDATED));

//        request.setAttribute(Attributes.ALERT_SUCCESS, true);
//        request.setAttribute(Attributes.ALERT_MESSAGE, MessageProperties.getMessage(USER_UPDATED));

        PageDto pageDto = new PageDto();
        final PageDto allRegisteredUsers = userService.findAllRegisteredUsers(pageDto);
        session.setAttribute(Attributes.MODEL, allRegisteredUsers);

        return urlBuilder.buildURL();
//        urlBuilder.setRedirect(false);

//         return new URLBuilder(true, ViewProperties.getPath(ViewProperties.ADMIN_USERS)).buildURL();
    }
}
