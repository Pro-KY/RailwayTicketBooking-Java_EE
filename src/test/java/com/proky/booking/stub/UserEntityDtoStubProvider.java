package com.proky.booking.stub;

import com.proky.booking.dto.PageDto;
import com.proky.booking.dto.UserDto;
import com.proky.booking.persistence.entity.User;
import com.proky.booking.persistence.entity.UserType;
import com.proky.booking.util.ModelMapperWrapper;
import com.proky.booking.util.PasswordEncryptor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserEntityDtoStubProvider {
    private final PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();
    private final ModelMapper modelMapper = ModelMapperWrapper.getInstance().getModelMapper();

    private final String FIRST_USER_HASHED_PASSWORD = "30b2989c477c6ccbdd4491479c62dce4ce4b9b2f3458b654a7bc02978d1f8f53";
    private final String SECOND_USER_HASHED_PASSWORD = "be0342d0b45a010fee5b58b8b697cd4a1230c404f9ef0259b956496730788ee4";
    private final String THIRD_USER_HASHED_PASSWORD = "82ce47b23e77e9c53e777172aacbccf2576c9141b8de2bbe4194bdd77035e09d";

    private static UserEntityDtoStubProvider mInstance;

    private UserEntityDtoStubProvider() { }

    public static UserEntityDtoStubProvider getInstance() {
        if (mInstance == null) {
            mInstance = new UserEntityDtoStubProvider();
        }
        return mInstance;
    }

    public User getAdminStub() {
        return new User(1L, "Василий", "Админский", "vas.admin@gmail.com",
                passwordEncryptor.encrypt("pass_1"), new UserType(2L));

    }

    public User getUserStub() {
        return new User(2L, "Геннадий", "Пасажирский", "gen.omel@gmail.com",
                passwordEncryptor.encrypt("pass_2"), new UserType(3L));
    }

    public UserDto getUserDto() {
        return modelMapper.map(getUserStub(), UserDto.class);
    }

    public UserDto getUserDtoWithValidEmailAndPassword() {
        return new UserDto.Builder().email("gen.omel@gmail.com").password("pass_2").build();
    }

    public UserDto getUserDtoWithInvalidPassword() {
        return new UserDto.Builder().email("gen.omel@gmail.com").password("pass").build();
    }

    public UserDto getUserDtoWithInvalidEmail() {
        return new UserDto.Builder().email("gen.omel@g").password("pass_2").build();
    }

    public UserDto getUserDtoWithNullEmail() {
        return new UserDto.Builder().email(null).password("pass_2").build();
    }

    public UserDto getUserDtoWithNullPassword() {
        return new UserDto.Builder().email("gen.omel@gmail.com").password(null).build();
    }

    public Optional<UserType> getOptionalUserUserType() {
        return Optional.of(getUserUserType());
    }

    public UserType getUserUserType() {
        return new UserType(1L, "passenger");
    }

    public List<User> getRegisteredPassengers() {
        final List<User> users = new ArrayList<>();
        final UserType userUserType = new UserType(3L);

        final User firstUser = new User(2L, "Геннадий", "Пасажирский", "gen.omel@gmail.com",
                FIRST_USER_HASHED_PASSWORD, userUserType);
        final User secondUser = new User(3L, "Валентина", "Перонова", "val.pir@gmail.com",
                SECOND_USER_HASHED_PASSWORD, userUserType);
        final User thirdUser = new User(4L, "Офанасий", "Билетский", "ofan.bill@gmail.com",
                THIRD_USER_HASHED_PASSWORD, userUserType);
        users.add(firstUser);
        users.add(secondUser);
        users.add(thirdUser);
        return users;
    }

    public List<UserDto> getRegisteredPassengersDtos() {
        final List<UserDto> userDtos = new ArrayList<>();

        final UserDto firstUserDto = new UserDto.Builder()
                .id("2")
                .firstName("Геннадий")
                .lastName("Пасажирский")
                .email("gen.omel@gmail.com").password(FIRST_USER_HASHED_PASSWORD).userTypeId("3").build();

        final UserDto secondUserDto = new UserDto.Builder()
                .id("2")
                .firstName("Валентина")
                .lastName("Перонова")
                .email("val.pir@gmail.com").password(SECOND_USER_HASHED_PASSWORD).userTypeId("3").build();

        final UserDto thirdUserDto = new UserDto.Builder()
                .id("2")
                .firstName("Офанасий")
                .lastName("Билетский")
                .email("ofan.bill@gmail.com").password(THIRD_USER_HASHED_PASSWORD).userTypeId("3").build();

        userDtos.add(firstUserDto);
        userDtos.add(secondUserDto);
        userDtos.add(thirdUserDto);
        return userDtos;
    }



}
