package com.proky.booking.dto;

import java.io.Serializable;

public class UserDto implements Serializable {
   private String id;
   private String firstName;
   private String lastName;
   private String email;
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
