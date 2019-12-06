package com.proky.booking.presentation.command.admin;

import com.proky.booking.dto.PageDto;
import com.proky.booking.presentation.command.ICommand;
import com.proky.booking.service.PaginationService;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.service.UserService;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.proky.booking.util.properties.ViewProperties.ADMIN_USERS;


public class AllUsersCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(AllUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final PageDto sessionPageDto = PaginationService.getCurrentPageDto(session);
        sessionPageDto.setRequestParameters(request);

        final UserService userService = ServiceFactory.getInstance().getUserService();
        final PageDto foundUsersPerPage = userService.findAllRegisteredUsers(sessionPageDto);
        session.setAttribute(Attributes.USERS_PAGE_DTO, foundUsersPerPage);

        return ViewProperties.getValue(ADMIN_USERS);
    }
}
