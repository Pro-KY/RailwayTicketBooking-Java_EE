package com.proky.booking.presentation.command;

import com.proky.booking.persistence.entity.Station;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.service.StationService;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.constans.http.Commands;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.proky.booking.util.properties.ViewProperties.INDEX;

public class HomeCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(HomeCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("home command called");
        final HttpSession session = request.getSession();
        session.removeAttribute(Attributes.MODEL);
        return Commands.REDIRECT + ViewProperties.getValue(INDEX);
    }
}
