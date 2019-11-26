package com.proky.booking.presentation.command;

import com.proky.booking.dto.PageDto;
import com.proky.booking.service.PaginationService;
import com.proky.booking.service.TrainService;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.util.UrlBuilder;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.proky.booking.util.constans.http.Parameters.*;
import static com.proky.booking.util.properties.ViewProperties.INDEX;

public class FindTrainsCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(FindTrainsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final UrlBuilder urlBuilder = new UrlBuilder(true, ViewProperties.getValue(INDEX));

        final String stationId = request.getParameter(GOING_TO);
        final String dateUI = request.getParameter(DEPARTURE_DATE);
        final String timeUI = request.getParameter(DEPARTURE_TIME);

        final HttpSession session = request.getSession();
        final PageDto sessionPageDto = PaginationService.getCurrentPageDto(session);
//        log.debug("before sessionPageDto: {}", sessionPageDto);
        sessionPageDto.setRequestParameters(request);
//        log.debug("after sessionPageDto: {}", sessionPageDto);

        final TrainService trainService = ServiceFactory.getInstance().getTrainService();
        final PageDto foundTrainsPerPage = trainService.findTrains(sessionPageDto, dateUI, timeUI, stationId);
//        log.info("foundTrainsPerPage == null {}", foundTrainsPerPage == null);

        session.setAttribute(Attributes.MODEL, foundTrainsPerPage);

        urlBuilder.setParameter(GOING_TO, stationId);
        urlBuilder.setParameter(DEPARTURE_DATE, dateUI);
        urlBuilder.setParameter(DEPARTURE_TIME, timeUI);

        return urlBuilder.buildURL();
    }
}
