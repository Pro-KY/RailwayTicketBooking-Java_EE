package com.proky.booking.persistence.dao.factory;

import com.proky.booking.persistence.dao.*;
import com.proky.booking.persistence.dao.mysql.*;

public class MysqlDaoFactory implements DaoFactory {
    private static MysqlDaoFactory mInstance;

    private MysqlDaoFactory() {}

    public static MysqlDaoFactory getInstance() {
        if (mInstance == null) {
            mInstance = new MysqlDaoFactory();
        }
        return mInstance;
    }

    @Override
    public IUserDao getUserDao() {
        return UserDaoImpl.getInstance();
    }

    @Override
    public IUserTypeDao getUserTypeDao() {
        return UserTypeDaoImpl.getInstance();
    }

    @Override
    public ITrainDao getTrainDao() {
        return TrainDaoImpl.getInstance();
    }

    @Override
    public IRouteStationDao getRouteStationDao() {
        return RouteStationDaoImpl.getInstance();
    }

    @Override
    public IStationDao getStationDao() {
        return StationDaoImpl.getInstance();
    }

    @Override
    public IInvoiceDao getInvoiceDao() {
        return InvoiceDaoImpl.getInstance();
    }

    @Override
    public IRouteDao getRouteDao() {
        return RouteDaoImpl.getInstance();
    }
}
