package com.proky.booking.presentation.command.fragment;

import com.proky.booking.presentation.command.ICommand;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.proky.booking.util.constans.Attributes.GET_FRAGMENT_TICKET_BOOKING;
import static com.proky.booking.util.properties.ViewProperties.*;

public class TicketBookingFragmentCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(TicketBookingFragmentCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("TicketBookingFragmentCommand CALLED");
        request.setAttribute(Attributes.GET_FRAGMENT_TICKET_BOOKING, ViewProperties.getPath(FRAGMENT_TICKET_BOOKING));
        return ViewProperties.getPath(INDEX);
    }
}
