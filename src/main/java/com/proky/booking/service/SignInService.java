package com.proky.booking.service;

import com.proky.booking.exception.ServiceException;
import com.proky.booking.persistence.dao.IUserDao;
import com.proky.booking.persistence.dao.factory.DaoFactory;
import com.proky.booking.persistence.entity.User;
import com.proky.booking.util.PasswordEncryptor;
import com.proky.booking.util.properties.MessageProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class SignInService {
    private static final Logger log = LogManager.getLogger(SignInService.class);
    private DaoFactory daoFactory;

    SignInService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    //tODO: refactor later
    public User signIn(User enteredData) {
        final IUserDao userDao = daoFactory.getUserDao();
        final Optional<User> foundUser = userDao.findByEmail(enteredData.getEmail());

        return foundUser.filter(user -> {
            final String enteredPassword = enteredData.getPassword();
//            final String encryptedPassword = PasswordEncryptor.getInstance().encrypt(enteredPassword);
//            return encryptedPassword.equals(user.getPassword()); // true
            return enteredPassword.equals(user.getPassword());
        }).orElseThrow(() -> new ServiceException(MessageProperties.AUTHORIZATION_ERROR));
    }
}
