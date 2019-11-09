package com.proky.booking.presentation.command;

import com.proky.booking.dto.FindTrainDto;
import com.proky.booking.util.constans.Commands;
import com.proky.booking.util.constans.LocaleEnum;
import com.proky.booking.util.properties.LocalizationProperties;
import com.proky.booking.util.properties.ViewProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import static com.proky.booking.util.properties.ViewProperties.INDEX;

public class EmptyCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(EmptyCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("empty command");

        final LocalizationProperties localizationProperties = new LocalizationProperties(LocaleEnum.EN);
        final Map<String, String> stations = localizationProperties.getStations();

        final FindTrainDto findTrainDto = new FindTrainDto(stations);
        request.setAttribute("findTrainDto", findTrainDto);

        return ViewProperties.getPath(INDEX);
//        return Commands.REDIRECT + ViewProperties.getPath(INDEX);
    }
}
