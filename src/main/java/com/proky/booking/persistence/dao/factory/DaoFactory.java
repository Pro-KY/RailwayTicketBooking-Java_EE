package com.proky.booking.persistence.dao.factory;


import com.proky.booking.persistence.dao.*;

/**
 * Factory for creating Dao objects.
 */
public interface DaoFactory {
    IUserDao getUserDao();
    IUserTypeDao getUserTypeDao();
    ITrainDao getTrainDao();
    IRouteStationDao getRouteStationDao();
    IStationDao getStationDao();
    IInvoiceDao getInvoiceDao();
    IRouteDao getRouteDao();
}
