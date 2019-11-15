package com.proky.booking.presentation.command;

import com.proky.booking.dto.TicketBookingDto;
import com.proky.booking.persistence.entity.User;
import com.proky.booking.util.URLBuilder;
import com.proky.booking.util.command.HttpRequestDataBinder;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.proky.booking.util.properties.ViewProperties.*;


public class InvoiceCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(InvoiceCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final User user = (User)session.getAttribute(Attributes.USER);


        final HttpRequestDataBinder requestDataBinder = HttpRequestDataBinder.getInstance();
        final TicketBookingDto ticketBookingDto = requestDataBinder.bindToDto(request, TicketBookingDto.class);
        log.info(ticketBookingDto);

        request.getSession().setAttribute(Attributes.CURRENT_FRAGMENT, ViewProperties.getPath(FRAGMENT_INVOICE));
        final String indexViewPath = ViewProperties.getPath(INDEX);
        URLBuilder urlBuilder = new URLBuilder(true, indexViewPath);

        return urlBuilder.buildURL();
    }
}
