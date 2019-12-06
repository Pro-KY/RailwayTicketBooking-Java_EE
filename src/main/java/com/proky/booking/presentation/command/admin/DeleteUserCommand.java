package com.proky.booking.presentation.command.admin;

import com.proky.booking.dto.PageDto;
import com.proky.booking.dto.UserDto;
import com.proky.booking.presentation.command.CommandUtil;
import com.proky.booking.util.HttpRequestDataBinder;
import com.proky.booking.presentation.command.ICommand;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.service.UserService;
import com.proky.booking.util.UrlBuilder;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.proky.booking.util.properties.ViewProperties.ADMIN_USERS;

public class DeleteUserCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(DeleteUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final HttpRequestDataBinder requestDataBinder = HttpRequestDataBinder.getInstance();
        final UserDto userDto = requestDataBinder.bindToDto(request, UserDto.class);
        final Long userId = Long.parseLong(userDto.getId());

        final UserService userService = ServiceFactory.getInstance().getUserService();
        userService.deleteUser(userId);

        final HttpSession session = request.getSession();
        final UrlBuilder urlBuilder = new UrlBuilder(true, ViewProperties.getValue(ADMIN_USERS));
        CommandUtil.setAlertAttributes(true, "alert.user.deleted", session);

        PageDto pageDto = new PageDto();
        final PageDto allRegisteredUsers = userService.findAllRegisteredUsers(pageDto);
        session.setAttribute(Attributes.MODEL, allRegisteredUsers);

        return urlBuilder.buildURL();
    }
}
