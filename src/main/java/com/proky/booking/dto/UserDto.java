package com.proky.booking.dto;

import com.proky.booking.validation.annotation.Email;
import com.proky.booking.validation.annotation.Length;

import java.io.Serializable;

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
