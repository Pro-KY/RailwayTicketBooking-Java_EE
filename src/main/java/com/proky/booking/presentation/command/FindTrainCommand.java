package com.proky.booking.presentation.command;

import com.proky.booking.util.SqlDateTimeConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

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

        final SqlDateTimeConverter instance = SqlDateTimeConverter.getInstance();
        final Time time = instance.convertToSqlTime(timeUI);
        final Date date = instance.convertToSqlDate(dateUI);



        return null;
    }
}
