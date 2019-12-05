package com.proky.booking.presentation.command;

import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.validation.ValidationResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class CommandUtil {
    static String getReferer(HttpServletRequest request) {
        return request.getHeader("referer");
    }

    static void setValidationResultToSession(HttpSession session, ValidationResult validationResult) {
        session.setAttribute(Attributes.VALIDATION, validationResult);
    }
}
