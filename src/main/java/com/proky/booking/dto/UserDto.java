package com.proky.booking.dto;

import com.proky.booking.validation.annotation.Email;
import com.proky.booking.validation.annotation.Length;

import java.io.Serializable;
import java.util.Objects;

public class UserDto implements Serializable {
   private String id;

   @Length(min = 2, max = 30)
   private String firstName;

   @Length(min = 2, max = 30)
   private String lastName;

   @Email
   @Length(min = 6, max = 30)
   private String email;

   @Length(min = 6, max = 30)
   private String password;

   private String userTypeId;

   public UserDto() {}

   private UserDto(Builder builder) {
      setId(builder.id);
      setFirstName(builder.firstName);
      setLastName(builder.lastName);
      setEmail(builder.email);
      setPassword(builder.password);
      setUserTypeId(builder.userTypeId);
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getUserTypeId() {
      return userTypeId;
   }

   public void setUserTypeId(String userTypeId) {
      this.userTypeId = userTypeId;
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

   public static final class Builder {
      private String id;
      private String firstName;
      private String lastName;
      private String email;
      private String password;
      private String userTypeId;

      public Builder() {
      }

      public Builder id(String val) {
         id = val;
         return this;
      }

      public Builder firstName(String val) {
         firstName = val;
         return this;
      }

      public Builder lastName(String val) {
         lastName = val;
         return this;
      }

      public Builder email(String val) {
         email = val;
         return this;
      }

      public Builder password(String val) {
         password = val;
         return this;
      }

      public Builder userTypeId(String val) {
         userTypeId = val;
         return this;
      }

      public UserDto build() {
         return new UserDto(this);
      }
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      UserDto userDto = (UserDto) o;
      return Objects.equals(id, userDto.id) &&
              Objects.equals(firstName, userDto.firstName) &&
              Objects.equals(lastName, userDto.lastName) &&
              Objects.equals(email, userDto.email) &&
              Objects.equals(password, userDto.password) &&
              Objects.equals(userTypeId, userDto.userTypeId);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, firstName, lastName, email, password, userTypeId);
   }


   @Override
   public String toString() {
      return "UserDto{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", email='" + email + '\'' +
              ", password='" + password + '\'' +
              ", userTypeId='" + userTypeId + '\'' +
              '}';
   }
}
