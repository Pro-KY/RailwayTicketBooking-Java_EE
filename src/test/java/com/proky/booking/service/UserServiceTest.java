package com.proky.booking.service;

import com.proky.booking.dto.PageDto;
import com.proky.booking.dto.UserDto;
import com.proky.booking.exception.ServiceException;
import com.proky.booking.persistence.dao.IUserDao;
import com.proky.booking.persistence.dao.IUserTypeDao;
import com.proky.booking.persistence.dao.factory.DaoFactory;
import com.proky.booking.persistence.entity.User;
import com.proky.booking.persistence.entity.UserType;
import com.proky.booking.stub.PageDtoStubProvider;
import com.proky.booking.stub.UserDataStubProvider;
import com.proky.booking.util.constans.UserTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private final Long adminId = 1L;
    private final Long userId = 2L;

    @Mock
    private DaoFactory daoFactory;
    @Mock
    private IUserDao userDao;

    @Mock
    private IUserTypeDao userTypeDao;

    @InjectMocks
    private UserService userService;
    private final UserDataStubProvider userDataStubProvider = UserDataStubProvider.getInstance();
    private final PageDtoStubProvider pageDtoStubProvider = PageDtoStubProvider.getInstance();


    @Test
    public void isAdministratorWithAdministratorTest() {

        final User adminStub = userDataStubProvider.getAdminStub();
        final String adminType = UserTypeEnum.ADMIN.type;
        final Optional<UserType> optionalAdminType = Optional.of(new UserType(2L, adminType));

        when(daoFactory.getUserTypeDao()).thenReturn(userTypeDao);
        when(userTypeDao.findByType(adminType)).thenReturn(optionalAdminType);

        assertTrue(userService.isAdministrator(adminStub));
        verify(userTypeDao, times(1)).findByType(anyString());
    }

    @Test
    public void isAdministratorWithNotAdministratorTest() {
        final User userStub = userDataStubProvider.getUserStub();
        final String adminType = UserTypeEnum.ADMIN.type;
        final Optional<UserType> optionalAdminType = Optional.of(new UserType(2L, adminType));

        when(daoFactory.getUserTypeDao()).thenReturn(userTypeDao);
        when(userTypeDao.findByType(adminType)).thenReturn(optionalAdminType);

        assertFalse(userService.isAdministrator(userStub));
        verify(userTypeDao, times(1)).findByType(anyString());
    }

    @Test(expected = ServiceException.class)
    public void isAdministratorWithIncorrectUserTypeTest() {
        final User adminStub = userDataStubProvider.getAdminStub();
        final String incorrectUserType = "incorrectType";

        when(daoFactory.getUserTypeDao()).thenReturn(userTypeDao);
        when(userTypeDao.findByType(incorrectUserType)).thenReturn(Optional.empty());

        userService.isAdministrator(adminStub);
    }

    @Test
    public void findUserByIdWhenUserIsFoundTest() {
        final Optional<User> expected = Optional.of(userDataStubProvider.getAdminStub());
        when(daoFactory.getUserDao()).thenReturn(userDao);
        when(userDao.findById(adminId)).thenReturn(expected);

        User actual = userService.findUserById(adminId);
        assertEquals(expected.get(), actual);
        verify(userDao, times(1)).findById(adminId);
    }

    @Test(expected = ServiceException.class)
    public void findUserByIdWhenUserIsNotFoundTest() {
        when(daoFactory.getUserDao()).thenReturn(userDao);
        when(userDao.findById(adminId)).thenReturn(Optional.empty());
        userService.findUserById(adminId);
    }

    @Test
    public void updateUserTest() {
        final UserDto userDto = userDataStubProvider.getUserDto();
        final Optional<User> expected = Optional.of(userDataStubProvider.getUserStub());
        when(daoFactory.getUserDao()).thenReturn(userDao);
        when(userDao.findById(userId)).thenReturn(expected);

        userService.updateUser(userDto);
        verify(userDao, times(1)).update(any(User.class));
    }

    @Test
    public void deleteUserWithValidUserId() {
        final Optional<User> expectedOptionalUser = Optional.of(userDataStubProvider.getUserStub());
        final User expectedUser = UserDataStubProvider.getInstance().getUserStub();
        when(daoFactory.getUserDao()).thenReturn(userDao);
        when(userDao.findById(userId)).thenReturn(expectedOptionalUser);
        when(userDao.delete(expectedUser)).thenReturn(true);

        assertTrue(userService.deleteUser(userId));
        verify(userDao, times(1)).delete(any(User.class));
    }

    @Test(expected = ServiceException.class)
    public void deleteUserWithInvalidUserId() {
        final Long invalidUserId = -1L;

        final Optional<User> expectedOptionalUser = Optional.empty();
        when(daoFactory.getUserDao()).thenReturn(userDao);
        when(userDao.findById(invalidUserId)).thenReturn(expectedOptionalUser);

        assertTrue(userService.deleteUser(invalidUserId));
        verify(userDao, times(1)).delete(any(User.class));
    }

    @Test
    public void signInWithValidEmailAndPasswordTest() {
        final Optional<User> expectedOptionalUser = Optional.of(userDataStubProvider.getUserStub());
        final UserDto userDto = userDataStubProvider.getUserDtoWithValidEmailAndPassword();

        when(daoFactory.getUserDao()).thenReturn(userDao);
        when(userDao.findByEmail(userDto.getEmail())).thenReturn(expectedOptionalUser);

        final User actualUser = userService.signIn(userDto);

        assertEquals(expectedOptionalUser.get(), actualUser);
        verify(userDao, times(1)).findByEmail(any(String.class));
    }

    @Test(expected = ServiceException.class)
    public void signInWithInvalidPasswordTest() {
        final UserDto userDtoWithInvalidPassword = userDataStubProvider.getUserDtoWithInvalidPassword();
        when(daoFactory.getUserDao()).thenReturn(userDao);
        when(userDao.findByEmail(userDtoWithInvalidPassword.getEmail())).thenReturn(Optional.empty());

        userService.signIn(userDtoWithInvalidPassword);

        verify(userDao, times(1)).findByEmail(any(String.class));
    }

    @Test(expected = ServiceException.class)
    public void signInWithInvalidEmailTest() {
        final UserDto userDtoWithInvalidPassword = userDataStubProvider.getUserDtoWithInvalidEmail();
        when(daoFactory.getUserDao()).thenReturn(userDao);
        when(userDao.findByEmail(userDtoWithInvalidPassword.getEmail())).thenReturn(Optional.empty());

        userService.signIn(userDtoWithInvalidPassword);

        verify(userDao, times(1)).findByEmail(any(String.class));
    }

    @Test(expected = ServiceException.class)
    public void signUpWithTheSameEmailTest() {
        when(daoFactory.getUserDao()).thenReturn(userDao);

        final Optional<User> expectedOptionalUser = Optional.of(userDataStubProvider.getUserStub());
        final UserDto userDto = userDataStubProvider.getUserDtoWithValidEmailAndPassword();
        when(userDao.findByEmail(userDto.getEmail())).thenReturn(expectedOptionalUser);

        userService.signUp(userDto);
        verify(userDao, times(1)).findByEmail(any(String.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void signUpWithNullEmailTest() {
        when(daoFactory.getUserDao()).thenReturn(userDao);
        when(daoFactory.getUserTypeDao()).thenReturn(userTypeDao);

        final UserDto userDto = userDataStubProvider.getUserDtoWithNullEmail();
        when(userDao.findByEmail(userDto.getEmail())).thenReturn(Optional.empty());

        userService.signUp(userDto);
        verify(userDao, times(0)).findByEmail(any(String.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void signUpWithNullPasswordTest() {
        when(daoFactory.getUserDao()).thenReturn(userDao);
        when(daoFactory.getUserTypeDao()).thenReturn(userTypeDao);

        final UserDto userDto = userDataStubProvider.getUserDtoWithNullPassword();
        when(userDao.findByEmail(userDto.getEmail())).thenReturn(Optional.empty());

        userService.signUp(userDto);
        verify(userDao, times(0)).findByEmail(any(String.class));
    }

    @Test
    public void signUpWithValidParametersTest() {
        when(daoFactory.getUserDao()).thenReturn(userDao);
        when(daoFactory.getUserTypeDao()).thenReturn(userTypeDao);
        final UserDto validUserToSignUp = userDataStubProvider.getUserDto();
        final Optional<UserType> userUserType = userDataStubProvider.getOptionalUserUserType();

        when(userDao.findByEmail(validUserToSignUp.getEmail())).thenReturn(Optional.empty());
        when(userTypeDao.findByType(UserTypeEnum.USER.type)).thenReturn(userUserType);

        userService.signUp(validUserToSignUp);

        verify(userTypeDao, times(1)).findByType(any(String.class));
        verify(userDao, times(1)).findByEmail(any(String.class));
        verify(userDao, times(1)).save(any(User.class));
    }


    @Test
    public void findAllRegisteredUsersWithDefaultPageDto() {
        final Long expectedUsersAmount = 4L;
        final int registeredPassengersDtosSize = 3;
        final PageDto defaultPageDto = pageDtoStubProvider.getDefaultPageDto();

        final UserType userUserType = userDataStubProvider.getUserUserType();
        final List<User> expectedPassengersList = userDataStubProvider.getRegisteredPassengers();
        final List<UserDto> registeredPassengersDtos = userDataStubProvider.getRegisteredPassengersDtos();

        when(daoFactory.getUserDao()).thenReturn(userDao);
        when(daoFactory.getUserTypeDao()).thenReturn(userTypeDao);

        when(userTypeDao.findByType(UserTypeEnum.USER.type)).thenReturn(Optional.of(userUserType));
        when(userDao.countAllByType(userUserType)).thenReturn(expectedUsersAmount);
        when(userDao.findAllByType(any(UserType.class), anyLong(), anyLong())).thenReturn(expectedPassengersList);

        final PageDto expectedPageDto = new PageDto.Builder()
                .currentPageIndex(0L)
                .allPagesAmount(2L)
                .startPageIndex(0L)
                .endPageIndex(1L)
                .pageSize("3")
                .isNextClicked(false)
                .isPreviousClicked(false)
                .isLeftButtonDisabled(true)
                .isRightButtonDisabled(false)
                .pageList(registeredPassengersDtos).build();

        final PageDto actualPageDto = userService.findAllRegisteredUsers(defaultPageDto);
        assertEquals(registeredPassengersDtosSize, actualPageDto.getPageList().size());
        assertEquals(expectedPageDto, actualPageDto);
    }
}
