package com.proky.booking.service;

import com.proky.booking.dto.UserDto;
import com.proky.booking.exception.ServiceException;
import com.proky.booking.persistence.dao.IUserDao;
import com.proky.booking.persistence.dao.IUserTypeDao;
import com.proky.booking.persistence.dao.factory.DaoFactory;
import com.proky.booking.persistence.entity.User;
import com.proky.booking.persistence.entity.UserType;
import com.proky.booking.util.ModelMapperWrapper;
import com.proky.booking.util.PasswordEncryptor;
import com.proky.booking.util.constans.UserTypeEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.util.Optional;


public class SignUpService {
    private static final Logger log = LogManager.getLogger(SignUpService.class);
    private DaoFactory daoFactory;

    SignUpService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void signUp(UserDto userDto) {
        final IUserDao userDao = daoFactory.getUserDao();
        final IUserTypeDao userTypeDao = daoFactory.getUserTypeDao();

        final Optional<User> foundUser = userDao.findByEmail(userDto.getEmail());
        if (foundUser.isPresent()) {
            throw new ServiceException("alert.user.exist");
        } else {
            final PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();
            final String encryptedPassword = passwordEncryptor.encrypt(userDto.getPassword());
            final UserType userType = userTypeDao
                    .findByType(UserTypeEnum.USER.type)
                    .orElseThrow(() -> new ServiceException("alert.entity.notfound"));

            final ModelMapper modelMapper = ModelMapperWrapper.getInstance().getModelMapper();
            final User newUser = modelMapper.map(userDto, User.class);
            newUser.setPassword(encryptedPassword);
            newUser.setUserType(userType);

            log.info(newUser);

            userDao.save(newUser);
        }
    }
}
