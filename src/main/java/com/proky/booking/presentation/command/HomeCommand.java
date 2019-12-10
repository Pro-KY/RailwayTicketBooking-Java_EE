package com.proky.booking.presentation.command;

import com.proky.booking.persistence.entity.Station;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.service.StationService;
import com.proky.booking.util.UrlBuilder;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.constans.http.Commands;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.proky.booking.util.properties.ViewProperties.INDEX;

public class HomeCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(HomeCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        final HttpSession session = request.getSession();

        if (session.getAttribute(Attributes.STATIONS) == null) {
            final ServiceFactory serviceFactory = ServiceFactory.getInstance();
            final StationService stationService = serviceFactory.getStationService();
            final List<Station> allStations = stationService.findAllStations();
            session.setAttribute(Attributes.STATIONS, allStations);
        }

        return new UrlBuilder(true, request.getContextPath(),ViewProperties.getValue(INDEX)).buildURL();
    }
}
