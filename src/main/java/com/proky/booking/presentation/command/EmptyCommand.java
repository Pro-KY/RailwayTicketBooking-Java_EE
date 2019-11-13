package com.proky.booking.presentation.command;

import com.proky.booking.persistence.entity.Station;
import com.proky.booking.service.ServiceFactory;
import com.proky.booking.service.StationService;
import com.proky.booking.util.constans.Commands;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.proky.booking.util.properties.ViewProperties.INDEX;

public class EmptyCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(EmptyCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("empty command");
        final ServiceFactory instance = ServiceFactory.getInstance();
        final StationService stationService = instance.getStationService();
        final List<Station> allStations = stationService.findAllStations();

        request.getSession().setAttribute("stations", allStations);
        return Commands.REDIRECT + ViewProperties.getPath(INDEX);
    }
}
