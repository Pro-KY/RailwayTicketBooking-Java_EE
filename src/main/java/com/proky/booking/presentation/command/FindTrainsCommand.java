package com.proky.booking.presentation.command;

import com.proky.booking.dto.PageDto;
import com.proky.booking.service.TrainService;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.constans.Commands;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.proky.booking.util.constans.Parameters.*;
import static com.proky.booking.util.properties.ViewProperties.INDEX;

public class FindTrainsCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(FindTrainsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final String stationId = request.getParameter(GOING_TO);
        final String dateUI = request.getParameter(DEPARTURE_DATE);
        final String timeUI = request.getParameter(DEPARTURE_TIME);

        final HttpSession session = request.getSession();
        final PageDto sessionPageDto = getCurrentPageDto(session);

        final TrainService trainService = ServiceFactory.getInstance().getFindTrainService();
        final PageDto foundTrainsPerPage = trainService.findTrains(sessionPageDto, dateUI, timeUI, stationId);

        session.setAttribute(Attributes.PAGE_DTO, foundTrainsPerPage);
        return Commands.REDIRECT + ViewProperties.getPath(INDEX);
    }

    private PageDto getCurrentPageDto(HttpSession session) {
        PageDto currentPageDto = (PageDto) session.getAttribute(Attributes.PAGE_DTO);
        if (currentPageDto == null) {
            currentPageDto = new PageDto();
        }

        return currentPageDto;
    }
}
