package com.proky.booking.persistence.dao.factory;

import com.proky.booking.persistence.dao.IUserDao;
import com.proky.booking.persistence.dao.mysql.UserDaoImpl;

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

}
