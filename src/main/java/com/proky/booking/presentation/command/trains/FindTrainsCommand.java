package com.proky.booking.presentation.command.trains;

import com.proky.booking.dto.PageDto;
import com.proky.booking.persistence.entity.Station;
import com.proky.booking.presentation.command.ICommand;
import com.proky.booking.service.PaginationService;
import com.proky.booking.service.StationService;
import com.proky.booking.service.TrainService;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.util.UrlBuilder;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static com.proky.booking.util.constans.http.Parameters.*;
import static com.proky.booking.util.properties.ViewProperties.INDEX;

public class FindTrainsCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(FindTrainsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final UrlBuilder urlBuilder = new UrlBuilder(true, request.getContextPath(), ViewProperties.getValue(INDEX));
        final HttpSession session = request.getSession();

        final String stationId = request.getParameter(GOING_TO);
        final String dateUI = request.getParameter(DEPARTURE_DATE);
        final String timeUI = request.getParameter(DEPARTURE_TIME);

        final PageDto sessionPageDto = PaginationService.getCurrentPageDto(session);
        sessionPageDto.setRequestParameters(request);
        log.info("pageDto in {}", sessionPageDto);

        final ServiceFactory serviceFactory = ServiceFactory.getInstance();
        final PageDto foundTrainsPerPage = serviceFactory.getTrainService().findTrains(sessionPageDto, dateUI, timeUI, stationId);
        log.info("pageDto out {}", foundTrainsPerPage);

        session.setAttribute(Attributes.TRAINS_PAGE_DTO, foundTrainsPerPage);

        final Object attribute = session.getAttribute(Attributes.STATIONS);
        log.info(attribute == null);

        final List<Station> allStations = serviceFactory.getStationService().findAllStations();
        session.setAttribute(Attributes.STATIONS, allStations);

        urlBuilder.setAttribute(GOING_TO, stationId);
        urlBuilder.setAttribute(DEPARTURE_DATE, dateUI);
        urlBuilder.setAttribute(DEPARTURE_TIME, timeUI);

        return urlBuilder.buildURL();
    }
}
