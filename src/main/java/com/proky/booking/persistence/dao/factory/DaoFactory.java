package com.proky.booking.persistence.dao.factory;


import com.proky.booking.persistence.dao.ITrainDao;
import com.proky.booking.persistence.dao.IUserDao;
import com.proky.booking.persistence.dao.IUserTypeDao;

public interface DaoFactory {
    IUserDao getUserDao();
    IUserTypeDao getUserTypeDao();
    ITrainDao getTrainDao();

}
