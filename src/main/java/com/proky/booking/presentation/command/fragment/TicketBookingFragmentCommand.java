package com.proky.booking.presentation.command.fragment;

import com.proky.booking.dto.TrainDto;
import com.proky.booking.presentation.command.ICommand;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.service.TrainService;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.constans.Parameters;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.proky.booking.util.properties.ViewProperties.*;

public class TicketBookingFragmentCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(TicketBookingFragmentCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("TicketBookingFragmentCommand CALLED");
        final String trainId = request.getParameter(Parameters.TRAIN_ID); // TODO: validate

        final ServiceFactory serviceFactory = ServiceFactory.getInstance();

        final TrainService trainService = serviceFactory.getTrainService();
        final TrainDto trainDto = trainService.findTrainById(Long.parseLong(trainId));

        request.getSession().setAttribute(Attributes.CURRENT_FRAGMENT, ViewProperties.getPath(FRAGMENT_TICKET_BOOKING));
        request.setAttribute(Parameters.FOUND_TRAIN, trainDto);
        return ViewProperties.getPath(INDEX);
    }
}
