package com.proky.booking.presentation.command;

import com.proky.booking.persistence.entity.User;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.service.UserService;
import com.proky.booking.util.command.HttpRequestDataBinder;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.constans.Parameters;
import com.proky.booking.util.properties.MessageProperties;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.proky.booking.util.properties.MessageProperties.USER_UPDATED;


public class UpdateUserCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(UpdateUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        log.debug("update user data");
        final HttpRequestDataBinder requestDataBinder = HttpRequestDataBinder.getInstance();
        final User user = requestDataBinder.bindToEntity(request, User.class);
        log.debug("mapped form-user: {}", user);

        final UserService userService = ServiceFactory.getInstance().getUserService();
        userService.updateUser(user);

        request.setAttribute(Attributes.ALERT_SUCCESS, true);
        request.setAttribute(Attributes.ALERT_MESSAGE, MessageProperties.getMessage(USER_UPDATED));

        return ViewProperties.getPath(ViewProperties.ADMIN_USERS);
//        urlBuilder.setRedirect(false);

//         return new URLBuilder(true, ViewProperties.getPath(ViewProperties.ADMIN_USERS)).buildURL();
    }
}
