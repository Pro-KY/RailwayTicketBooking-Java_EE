package com.proky.booking.persistence.dao.factory;


import com.proky.booking.persistence.dao.IUserDao;

public interface DaoFactory {
    IUserDao getUserDao();

}
