package com.proky.booking.persistence.mapper;

import com.proky.booking.persistence.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;

public class InvoiceMapper extends EntityMapper<Invoice> {
    private static final Logger log = LogManager.getLogger(InvoiceMapper.class);

    private static final String ID = "id";
    private static final String ID_ALIAS_IN_JOIN = "i_id";
    private static final String USER_ID = "user_id";
    private static final String TRAIN_ID = "train_id";
    private static final String SEATS_AMOUNT = "seats_amount";
    private static final String PRICE = "price";
    private static final String DATE_TIME = "date_time";

    private boolean mapTrain;
    private boolean mapUser;

    private EntityMapper<Train> trainEntityMapper;
    private EntityMapper<User> userEntityMapper;

    public InvoiceMapper(boolean useInJoin) {
        String idColumn = useInJoin ? ID_ALIAS_IN_JOIN : ID;
        columnNames = new String[]{idColumn, USER_ID, TRAIN_ID, SEATS_AMOUNT, PRICE, DATE_TIME};
    }

    @Override
    public Invoice mapToEntity(ResultSet resultSet) {

        try {
            final long id = resultSet.getLong(columnNames[0]);
            final long userId = resultSet.getLong(columnNames[1]);
            final long trainId = resultSet.getLong(columnNames[2]);
            final int seatsAmount = resultSet.getInt(columnNames[3]);
            final BigDecimal price = resultSet.getBigDecimal(columnNames[4]);
            final Timestamp dateTime = resultSet.getTimestamp(columnNames[5]);

            final Train train = mapTrain ? trainEntityMapper.mapToEntity(resultSet) : new Train(trainId);
            final User user = mapUser ? userEntityMapper.mapToEntity(resultSet) : new User(userId);
            mappedEntity = new Invoice(id, user, train, seatsAmount, price, dateTime);
        } catch (SQLException e) {
            log.error("Could not map to Invoice entity", e);
        }
        return mappedEntity;
    }

    public void mapTrainRelation(EntityMapper<Train> trainEntityMapper) {
        this.trainEntityMapper = trainEntityMapper;
        mapTrain = true;
    }

    public void mapUserRelation(EntityMapper<User> userEntityMapper) {
        this.userEntityMapper = userEntityMapper;
        mapUser = true;
    }
}
