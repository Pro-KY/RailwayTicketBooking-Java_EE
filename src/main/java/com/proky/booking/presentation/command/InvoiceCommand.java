package com.proky.booking.presentation.command;

import com.proky.booking.dto.InvoiceDto;
import com.proky.booking.dto.TicketBookingDto;
import com.proky.booking.persistence.entity.User;
import com.proky.booking.service.InvoiceService;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.util.URLBuilder;
import com.proky.booking.util.command.HttpRequestDataBinder;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Objects;

import static com.proky.booking.util.properties.ViewProperties.*;


public class InvoiceCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(InvoiceCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final HttpRequestDataBinder requestDataBinder = HttpRequestDataBinder.getInstance();
        final TicketBookingDto ticketBookingDto = requestDataBinder.bindToDto(request, TicketBookingDto.class);
        //TODO:validate input data
        log.info(ticketBookingDto);

        final ServiceFactory serviceFactory = ServiceFactory.getInstance();
        final InvoiceService invoiceService = serviceFactory.getInvoiceService();
        final InvoiceDto invoiceDto = invoiceService.calculateInvoice(ticketBookingDto);

        final User user = (User)session.getAttribute(Attributes.USER);
        final boolean isUserPresent = Objects.nonNull(user);

        String firstName = isUserPresent ? user.getFirstName() : ticketBookingDto.getFirstName();
        String lastName = isUserPresent ? user.getLastName() : ticketBookingDto.getLastName();
        invoiceDto.setUserFirstName(firstName);
        invoiceDto.setUserLastName(lastName);
        log.info("invoiceDto {}", invoiceDto);

        request.getSession().setAttribute(Attributes.MODEL, invoiceDto);
        request.getSession().setAttribute(Attributes.CURRENT_FRAGMENT, ViewProperties.getPath(FRAGMENT_INVOICE));
        final String viewPath = ViewProperties.getPath(INDEX);
        URLBuilder urlBuilder = new URLBuilder(true, viewPath);

        return urlBuilder.buildURL();
    }
}
