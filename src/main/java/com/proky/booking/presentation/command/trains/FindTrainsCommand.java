package com.proky.booking.presentation.command.trains;

import com.proky.booking.dto.FindTrainDto;
import com.proky.booking.dto.PageDto;
import com.proky.booking.dto.TicketBookingDto;
import com.proky.booking.persistence.entity.Station;
import com.proky.booking.presentation.command.CommandUtil;
import com.proky.booking.presentation.command.ICommand;
import com.proky.booking.service.PaginationService;
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

import java.util.List;

import static com.proky.booking.util.constans.http.Parameters.*;
import static com.proky.booking.util.properties.ViewProperties.INDEX;

public class FindTrainsCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(FindTrainsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final UrlBuilder urlBuilder = new UrlBuilder(true, request.getContextPath(), ViewProperties.getValue(INDEX));

        final HttpSession session = request.getSession();
        final HttpFormBinder requestDataBinder = HttpFormBinder.getInstance();
        final FindTrainDto findTrainDto = requestDataBinder.bindToDto(request, FindTrainDto.class);
        final ValidationService validationService = ValidationService.getInstance();
        final ValidationResult validationResult = validationService.validate(findTrainDto);

        if (validationResult.isSuccessfull()) {
            final PageDto requestPageDto = new PageDto();
            requestPageDto.setRequestParameters(request);
            log.info("pageDto in {}", requestPageDto);

            final String departureDate = findTrainDto.getDepartureDate();
            final String departureTime = findTrainDto.getDepartureTime();
            final String goingToId = findTrainDto.getGoingToId();

            final ServiceFactory serviceFactory = ServiceFactory.getInstance();
            final PageDto currentPageDto = serviceFactory.getTrainService()
                    .findTrains(requestPageDto, departureDate, departureTime, goingToId);

            log.info("pageDto out {}", currentPageDto);

            session.setAttribute(Attributes.TRAINS_PAGE_DTO, currentPageDto);

            final List<Station> allStations = serviceFactory.getStationService().findAllStations();
            session.setAttribute(Attributes.STATIONS, allStations);

            urlBuilder.setAttribute(GOING_TO_ID, goingToId);
            urlBuilder.setAttribute(DEPARTURE_DATE, departureDate);
            urlBuilder.setAttribute(DEPARTURE_TIME, departureTime);
        } else {
            CommandUtil.setValidationResultToSession(session, validationResult);
        }

        return urlBuilder.buildURL();
    }
}
