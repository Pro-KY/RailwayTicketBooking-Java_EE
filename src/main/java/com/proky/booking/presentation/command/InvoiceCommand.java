package com.proky.booking.presentation.command;

import com.proky.booking.dto.InvoiceDto;
import com.proky.booking.dto.TicketBookingDto;
import com.proky.booking.dto.UserDto;
import com.proky.booking.presentation.command.databinder.HttpRequestDataBinder;
import com.proky.booking.service.InvoiceService;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.util.UrlBuilder;
import com.proky.booking.util.constans.http.Attributes;
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

        final UserDto userDto = (UserDto)session.getAttribute(Attributes.USER);
        final boolean isUserPresent = Objects.nonNull(userDto);

        if (isUserPresent) {
            invoiceDto.setUserId(Long.parseLong(userDto.getId()));
            invoiceService.saveInvoice(invoiceDto);
        }

        String firstName = isUserPresent ? userDto.getFirstName() : ticketBookingDto.getFirstName();
        String lastName = isUserPresent ? userDto.getLastName() : ticketBookingDto.getLastName();
        invoiceDto.setUserFirstName(firstName);
        invoiceDto.setUserLastName(lastName);
        log.info("invoiceDto {}", invoiceDto);

        request.getSession().setAttribute(Attributes.MODEL, invoiceDto);
        final String viewPath = ViewProperties.getValue(INVOICE);
        UrlBuilder urlBuilder = new UrlBuilder(true, viewPath);

        return urlBuilder.buildURL();
    }
}
