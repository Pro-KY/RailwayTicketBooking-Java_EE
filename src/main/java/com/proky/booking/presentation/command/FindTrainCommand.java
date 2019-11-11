package com.proky.booking.presentation.command;

import com.proky.booking.dto.PageDto;
import com.proky.booking.dto.RouteDto;
import com.proky.booking.service.FindTrainService;
import com.proky.booking.service.PaginationService;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.util.SqlDateTimeConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.Date;
import java.sql.Time;

import static com.proky.booking.util.constans.Parameters.*;


public class FindTrainCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(FindTrainCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        final String stationId = request.getParameter(GOING_TO);
        final String dateUI = request.getParameter(DEPARTURE_DATE);
        final String timeUI = request.getParameter(DEPARTURE_TIME);

        final PaginationService paginationService = new PaginationService();

        final HttpSession session = request.getSession();
        final PageDto currentPageDto = paginationService.getCurrentPageDto(session);

        final FindTrainService findTrainService = ServiceFactory.getInstance().getFindTrainService();
        final PageDto<RouteDto> foundTrainsPerPage = findTrainService.findTrain(currentPageDto, dateUI, timeUI, stationId);

        return null;
    }
}
