package com.proky.booking.service;

import com.proky.booking.dto.UserDto;
import com.proky.booking.exception.ServiceException;
import com.proky.booking.persistence.dao.IUserDao;
import com.proky.booking.persistence.dao.factory.DaoFactory;
import com.proky.booking.persistence.entity.User;
import com.proky.booking.util.PasswordEncryptor;
import com.proky.booking.util.properties.MessageProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.proky.booking.util.properties.MessageProperties.AUTHORIZATION_ERROR;

public class SignInService {
    private static final Logger log = LogManager.getLogger(SignInService.class);
    private DaoFactory daoFactory;

    SignInService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public User signIn(UserDto enteredData) {
        final IUserDao userDao = daoFactory.getUserDao();
        final Optional<User> foundUser = userDao.findByEmail(enteredData.getEmail());

        return foundUser.filter(user -> {
            final String enteredPassword = enteredData.getPassword();
            final String encryptedPassword = PasswordEncryptor.getInstance().encrypt(enteredPassword);
            return encryptedPassword.equals(user.getPassword());
        }).orElseThrow(() -> new ServiceException(MessageProperties.getMessage(AUTHORIZATION_ERROR)));
    }
}
