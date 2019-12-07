package com.proky.booking.presentation.command;

import com.proky.booking.dto.InvoiceDto;
import com.proky.booking.dto.TicketBookingDto;
import com.proky.booking.dto.UserDto;
import com.proky.booking.service.InvoiceService;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.service.ValidationService;
import com.proky.booking.util.HttpFormBinder;
import com.proky.booking.util.UrlBuilder;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.ViewProperties;
import com.proky.booking.validation.ValidationResult;
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
        UrlBuilder urlBuilder = new UrlBuilder(true, ViewProperties.getValue(INVOICE));

        final HttpSession session = request.getSession();
        final HttpFormBinder requestDataBinder = HttpFormBinder.getInstance();
        final TicketBookingDto ticketBookingDto = requestDataBinder.bindToDto(request, TicketBookingDto.class);
        final ValidationService validationService = ValidationService.getInstance();
        ValidationResult validationResult;

        final UserDto userDto = (UserDto)session.getAttribute(Attributes.USER);
        final boolean isUserPresent = Objects.nonNull(userDto);

        if (isUserPresent) {
            validationResult = validationService.validate(ticketBookingDto, "seatsAmount");
        } else {
            validationResult = validationService.validate(ticketBookingDto);
        }

        if (validationResult.isSuccessfull()) {
            final ServiceFactory serviceFactory = ServiceFactory.getInstance();
            final InvoiceService invoiceService = serviceFactory.getInvoiceService();
            final InvoiceDto invoiceDto = invoiceService.calculateInvoice(ticketBookingDto, userDto);

            if (isUserPresent) {
                invoiceDto.setUserId(Long.parseLong(userDto.getId()));
                invoiceService.saveInvoice(invoiceDto);
            }
            request.getSession().setAttribute(Attributes.INVOICE_DTO, invoiceDto);
            urlBuilder.setContextPath(request.getContextPath());
        } else {
            urlBuilder.setViewPath(CommandUtil.getReferer(request));
            CommandUtil.setValidationResultToSession(session, validationResult);
        }

        return urlBuilder.buildURL();
    }
}
