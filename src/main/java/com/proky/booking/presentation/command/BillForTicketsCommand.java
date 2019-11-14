package com.proky.booking.presentation.command;

import com.proky.booking.persistence.entity.User;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.service.SignInService;
import com.proky.booking.service.ValidationService;
import com.proky.booking.util.URLBuilder;
import com.proky.booking.util.command.HttpRequestDataBinder;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.constans.Parameters;
import com.proky.booking.util.properties.MessageProperties;
import com.proky.booking.util.properties.ViewProperties;
import com.proky.booking.validation.ValidationResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.proky.booking.util.properties.MessageProperties.AUTHORIZATION_ERROR;
import static com.proky.booking.util.properties.ViewProperties.*;


public class BillForTicketsCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(BillForTicketsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();

        final String seatAmount = request.getParameter("seatAmount");
        final String userFirsName = request.getParameter(Parameters.USER_FIRST_NAME);
        log.info("seatAmount {}", seatAmount);
        log.info("userFirsName {}", userFirsName);

        request.getSession().setAttribute(Attributes.CURRENT_FRAGMENT, ViewProperties.getPath(FRAGMENT_BILL_FOR_TICKETS));
        final String indexViewPath = ViewProperties.getPath(INDEX);
        URLBuilder urlBuilder = new URLBuilder(true, indexViewPath);


        return urlBuilder.buildURL();
    }
}
