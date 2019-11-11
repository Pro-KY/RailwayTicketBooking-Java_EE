package com.proky.booking.persistence.mapper;

import com.proky.booking.persistence.entity.TrainType;
import com.proky.booking.persistence.entity.UserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainTypeMapper extends EntityMapper<TrainType> {
    private static final Logger log = LogManager.getLogger(TrainTypeMapper.class);

    private static final String ID = "id";
    private static final String ID_IN_JOIN = "tt_id";
    private static final String TYPE = "type";
    private static final String SEAT_PRICE = "seat_price";

    public TrainTypeMapper(boolean useInJoin) {
        String idColumn = useInJoin ? ID_IN_JOIN : ID;
        columnNames = new String[]{idColumn, TYPE, SEAT_PRICE};
    }

    @Override
    public TrainType mapToEntity(ResultSet resultSet) {
        try {
            long id = resultSet.getLong(columnNames[0]);
            final String type = resultSet.getString(columnNames[1]);
            final BigDecimal seatPrice = resultSet.getBigDecimal(columnNames[2]);
            mappedEntity =  new TrainType(id, type, seatPrice);
        } catch (SQLException e) {
            log.error("Could not map to TrainType entity", e);
        }
        return mappedEntity;
    }
}
