package com.proky.booking.presentation.command.trains;

import com.proky.booking.dto.TrainDto;
import com.proky.booking.presentation.command.ICommand;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.service.TrainService;
import com.proky.booking.util.constans.http.Parameters;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.proky.booking.util.properties.ViewProperties.*;

public class TrainBookingCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(TrainBookingCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final String trainId = request.getParameter(Parameters.TRAIN_ID); // TODO: validate data

        final ServiceFactory serviceFactory = ServiceFactory.getInstance();

        final TrainService trainService = serviceFactory.getTrainService();
        final TrainDto trainDto = trainService.findTrainById(Long.parseLong(trainId));

        request.setAttribute(Parameters.FOUND_TRAIN, trainDto);
        return ViewProperties.getValue(TRAIN_BOOKING);
    }
}
