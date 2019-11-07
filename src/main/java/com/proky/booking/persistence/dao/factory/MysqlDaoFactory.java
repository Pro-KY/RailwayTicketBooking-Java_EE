package com.proky.booking.persistence.dao.factory;

import com.proky.booking.persistence.dao.IUserDao;
import com.proky.booking.persistence.dao.IUserTypeDao;
import com.proky.booking.persistence.dao.mysql.UserDaoImpl;
import com.proky.booking.persistence.dao.mysql.UserTypeDaoImpl;

public class MysqlDaoFactory implements DaoFactory {
    private static MysqlDaoFactory instance = null;

    private MysqlDaoFactory() { }

    public static MysqlDaoFactory getInstance() {
        return (instance == null) ? new MysqlDaoFactory() : instance;
    }

    @Override
    public IUserDao getUserDao() {
        return UserDaoImpl.getInstance();
    }

    @Override
    public IUserTypeDao getUserTypeDao() {
        return UserTypeDaoImpl.getInstance();
    }

}
