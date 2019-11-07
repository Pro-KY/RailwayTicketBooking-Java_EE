package com.proky.booking.service;

import com.proky.booking.exception.ServiceException;
import com.proky.booking.persistence.dao.factory.DaoFactory;
import com.proky.booking.persistence.entity.User;
import com.proky.booking.util.PasswordEncryptor;

public class SignUpService {
    private DaoFactory daoFactory;

    public SignUpService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void signUp(User user) {


        final PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();
        final String encryptedPassword = passwordEncryptor.encryptPassword(user.getPassword());


        throw new ServiceException("oops =(");
    }
}
