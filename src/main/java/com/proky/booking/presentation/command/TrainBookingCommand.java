package com.proky.booking.presentation.command;

import com.proky.booking.dto.TrainDto;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.service.TrainService;
import com.proky.booking.util.constans.Parameters;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.proky.booking.util.properties.ViewProperties.*;

public class TrainBookingCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(TrainBookingCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("TrainBookingCommand CALLED");
        final String trainId = request.getParameter(Parameters.TRAIN_ID); // TODO: validate

        final ServiceFactory serviceFactory = ServiceFactory.getInstance();

        final TrainService trainService = serviceFactory.getTrainService();
        final TrainDto trainDto = trainService.findTrainById(Long.parseLong(trainId));

//        request.getSession().setAttribute(Attributes.CURRENT_FRAGMENT, ViewProperties.getPath(FRAGMENT_TICKET_BOOKING));
        request.setAttribute(Parameters.FOUND_TRAIN, trainDto);

//        return new URLBuilder(true, ViewProperties.getPath(FRAGMENT_TICKET_BOOKING)).buildURL();
        return ViewProperties.getPath(TRAIN_BOOKING);
    }
}
