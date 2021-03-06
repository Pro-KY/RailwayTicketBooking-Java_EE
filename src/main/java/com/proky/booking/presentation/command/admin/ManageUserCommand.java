package com.proky.booking.presentation.command.admin;

import com.proky.booking.dto.UserDto;
import com.proky.booking.persistence.entity.User;
import com.proky.booking.presentation.command.ICommand;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.service.UserService;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.constans.http.Parameters;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class ManageUserCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(ManageUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final String userId = request.getParameter(Parameters.USER_ID);

        final ServiceFactory serviceFactory = ServiceFactory.getInstance();
        final UserService userService = serviceFactory.getUserService();

        final User user = userService.findUserById(Long.parseLong(userId));
        final UserDto userDto = userService.getUserDtoByUser(user);

        request.setAttribute(Attributes.USER_DTO, userDto);
        return ViewProperties.getValue(ViewProperties.ADMIN_MANAGE_USER);
    }
}
