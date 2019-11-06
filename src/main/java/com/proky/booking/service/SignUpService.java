package com.proky.booking.service;

import com.proky.booking.exception.ServiceException;
import com.proky.booking.persistence.dao.factory.DaoFactory;
import com.proky.booking.persistence.entity.User;

public class SignUpService {
    private DaoFactory daoFactory;

    public SignUpService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void signUp(User user) {
        throw new ServiceException("oops =(");
    }
}
