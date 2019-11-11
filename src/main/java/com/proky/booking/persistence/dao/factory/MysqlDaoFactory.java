package com.proky.booking.persistence.dao.factory;

import com.proky.booking.persistence.dao.ITrainDao;
import com.proky.booking.persistence.dao.IUserDao;
import com.proky.booking.persistence.dao.IUserTypeDao;
import com.proky.booking.persistence.dao.mysql.TrainDaoImpl;
import com.proky.booking.persistence.dao.mysql.UserDaoImpl;
import com.proky.booking.persistence.dao.mysql.UserTypeDaoImpl;

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
}
