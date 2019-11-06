package com.proky.booking;

import com.proky.booking.persistence.entity.User;
import com.proky.booking.service.ValidationService;

import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        final ValidationService instance = ValidationService.getInstance();

        String s = "dfdf";
        boolean notValid = Pattern.matches("^[a-zA-Z ]*$", s);
        System.out.println(notValid);
    }
}
