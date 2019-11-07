package com.proky.booking.service;

import com.proky.booking.exception.ServiceException;
import com.proky.booking.persistence.dao.IUserDao;
import com.proky.booking.persistence.dao.IUserTypeDao;
import com.proky.booking.persistence.dao.factory.DaoFactory;
import com.proky.booking.persistence.entity.User;
import com.proky.booking.persistence.entity.UserType;
import com.proky.booking.util.PasswordEncryptor;
import com.proky.booking.util.constans.UserTypeEnum;
import com.proky.booking.util.properties.MessageProperties;

import java.util.Optional;

public class SignUpService {
    private DaoFactory daoFactory;

    public SignUpService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void signUp(User user) {
        final IUserDao userDao = daoFactory.getUserDao();
        final IUserTypeDao userTypeDao = daoFactory.getUserTypeDao();

        final Optional<User> foundUser = userDao.findByEmail(user.getEmail());
        if (foundUser.isPresent()) {
            throw new ServiceException(MessageProperties.USER_EXIST);
        } else {
            final PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();
            final String encryptedPassword = passwordEncryptor.encrypt(user.getPassword());
            final UserType userType = userTypeDao.findByType(UserTypeEnum.USER.type).orElseThrow(() -> new ServiceException(MessageProperties.NOT_FOUND_ENTITY));

            user.setPassword(encryptedPassword);
            user.setUserType(userType);

            userDao.save(user);
        }
    }
}
