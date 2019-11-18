package com.proky.booking.persistence.mapper;


import com.proky.booking.persistence.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainMapper extends EntityMapper<Train> {
    private static final Logger log = LogManager.getLogger(TrainMapper.class);

    private static final String ID = "id";
    private static final String TRAIN_ALIAS_IN_JOIN = "t_id";
    private static final String TRAIN_TYPE_ID = "train_type_id";
    private static final String ROUTE_ID = "route_id";
    private boolean mapRoute;
    private boolean mapTrainType;

    private EntityMapper<Route> routeEntityMapper;
    private EntityMapper<TrainType> trainTypeEntityMapper;

    public TrainMapper(boolean useInJoin) {
        String idColumn = useInJoin ? TRAIN_ALIAS_IN_JOIN : ID;
        columnNames = new String[] {idColumn, TRAIN_TYPE_ID, ROUTE_ID};
    }

    @Override
    public Train mapToEntity(ResultSet resultSet) {

        try {
            Long id = resultSet.getLong(columnNames[0]);
            final Long trainTypeId = resultSet.getLong(columnNames[1]);
            final Long routeId = resultSet.getLong(columnNames[2]);
            final TrainType trainType = mapTrainType ? trainTypeEntityMapper.mapToEntity(resultSet) : new TrainType(trainTypeId);
            final Route route = mapRoute ? routeEntityMapper.mapToEntity(resultSet) : new Route(routeId);

            mappedEntity = new Train(id, trainType, route);
        } catch (SQLException e) {
            log.error("Could not map to Train entity", e);
        }
        return mappedEntity;
    }

    public void mapRouteRelation(EntityMapper<Route> routeEntityMapper) {
        this.routeEntityMapper = routeEntityMapper;
        mapRoute = true;
    }

    public void mapTrainTypeRelation(EntityMapper<TrainType> trainTypeEntityMapper) {
        this.trainTypeEntityMapper = trainTypeEntityMapper;
        mapTrainType = true;
    }
}
