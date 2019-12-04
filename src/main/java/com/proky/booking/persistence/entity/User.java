package com.proky.booking.persistence.entity;

import com.proky.booking.validation.annotation.Email;

import java.util.Objects;

public class User extends Entity<Long> {
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String password;
    private UserType userType;

    public User() {}

    public User(Long id) {
        super(id);
    }

    public User(Long id, String firstName, String lastName, String email, String password, UserType userType) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(this.getId(), user.getId()) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(userType, user.userType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), firstName, lastName, email, password, userType);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id + '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
