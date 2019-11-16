package com.proky.booking.presentation.command;

import com.proky.booking.dto.InvoiceDto;
import com.proky.booking.dto.TicketBookingDto;
import com.proky.booking.dto.UserDto;
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

import static com.proky.booking.util.properties.ViewProperties.INVOICE;


public class ManageUserCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(ManageUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final HttpRequestDataBinder requestDataBinder = HttpRequestDataBinder.getInstance();
        final UserDto userDto = requestDataBinder.bindToEntity(request, UserDto.class);
        log.info(userDto);

        return null;
    }
}
