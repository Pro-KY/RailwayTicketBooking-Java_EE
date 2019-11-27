package com.proky.booking.service;

import com.proky.booking.annotation.Transactional;
import com.proky.booking.dto.PageDto;
import com.proky.booking.dto.UserDto;
import com.proky.booking.exception.ServiceException;
import com.proky.booking.persistence.dao.IUserDao;
import com.proky.booking.persistence.dao.IUserTypeDao;
import com.proky.booking.persistence.dao.factory.DaoFactory;
import com.proky.booking.persistence.entity.User;
import com.proky.booking.persistence.entity.UserType;
import com.proky.booking.util.constans.UserTypeEnum;
import com.proky.booking.util.properties.MessageProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private static final Logger log = LogManager.getLogger(UserService.class);

    private DaoFactory daoFactory;

    UserService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Transactional(readOnly = true)
    public PageDto findAllRegisteredUsers(PageDto pageDto) {

        final IUserDao userDao = daoFactory.getUserDao();
        final IUserTypeDao userTypeDao = daoFactory.getUserTypeDao();

        final UserType userType = userTypeDao.
                findByType(UserTypeEnum.USER.type)
                .orElseThrow(() -> new ServiceException(MessageProperties.NOT_FOUND_ENTITY));

        final Long allUsersAmount = userDao.countAllByType(userType);

        final PaginationService paginationService = new PaginationService(pageDto);
        paginationService.setAllRowsAmount(allUsersAmount);
        paginationService.calculatePagination();

        final long pageSize = paginationService.getPageSize();
        final long offSet = paginationService.getOffSet();
        log.info("pageSize {}", pageSize);
        log.info("offSet {}", offSet);

        final ModelMapper modelMapper = new ModelMapper();

        final List<UserDto> allPassengers = userDao
                .findAllByType(userType, pageSize, offSet)
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .peek(userDto -> log.info("userDto: {}", userDto))
                .collect(Collectors.toList());

        pageDto.setPageList(allPassengers);
        paginationService.updatePageDto();

        log.info("pageDto: {}", pageDto.toString());

        return pageDto;
    }

    public boolean isAdministrator(User authenticatedUser) {
        final UserType userType = authenticatedUser.getUserType();
        final IUserTypeDao userTypeDao = daoFactory.getUserTypeDao();

        final UserType adminUserType = userTypeDao.
                findByType(UserTypeEnum.ADMIN.type)
                .orElseThrow(() -> new ServiceException(MessageProperties.NOT_FOUND_ENTITY));

        return userType.equals(adminUserType);
    }

    public User findUserById(Long id) {
        return daoFactory.getUserDao().findById(id).orElseThrow(() -> new ServiceException(MessageProperties.NOT_FOUND_ENTITY));
    }

    public UserDto mapUserToDto(User user) {
        return new ModelMapper().map(user, UserDto.class);
    }

    @Transactional
    public void updateUser(UserDto userDto) {
        final Long userId = Long.parseLong(userDto.getId());
        final User user = findUserById(userId);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        final IUserDao userDao = daoFactory.getUserDao();
        userDao.update(user);
    }

    @Transactional
    public boolean deleteUser(Long userId) {
        final User user = daoFactory.getUserDao().findById(userId).orElseThrow(() -> new ServiceException(MessageProperties.NOT_FOUND_ENTITY));
        final IUserDao userDao = daoFactory.getUserDao();
        return userDao.delete(user);
    }
}

