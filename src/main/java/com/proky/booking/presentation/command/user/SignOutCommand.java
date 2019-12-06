package com.proky.booking.presentation.command.user;

import com.proky.booking.persistence.entity.Station;
import com.proky.booking.presentation.command.ICommand;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.service.StationService;
import com.proky.booking.util.UrlBuilder;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.constans.http.Commands;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.proky.booking.util.properties.ViewProperties.INDEX;


public class SignOutCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(SignOutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();

        UrlBuilder urlBuilder = new UrlBuilder(true, request.getContextPath());
        urlBuilder.setAttribute(Attributes.COMMAND, Commands.HOME);
        return urlBuilder.buildURL();
    }
}
