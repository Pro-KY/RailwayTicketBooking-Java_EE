package com.proky.booking.presentation.command;

import com.proky.booking.util.constans.UserTypeEnum;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.validation.ValidationResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandUtil {
    public static String getReferer(HttpServletRequest request) {
        return request.getHeader("referer");
    }

    public static void setValidationResultToSession(HttpSession session, ValidationResult validationResult) {
        session.setAttribute(Attributes.VALIDATION, validationResult);
    }

    public static UserTypeEnum getCurrentUserType(HttpSession session) {
        UserTypeEnum userType = UserTypeEnum.GUEST;
        if (session != null) {
            final Object userTypeAttribute = session.getAttribute(Attributes.USER_TYPE);
            if (userTypeAttribute != null) {
                userType = (UserTypeEnum) session.getAttribute(Attributes.USER_TYPE);
            }
        }
        return userType;
    }

    public static void setAlertAttributes(boolean alertSuccess, String messageKey, HttpSession session) {
        String alertType = alertSuccess ? Attributes.ALERT_SUCCESS : Attributes.ALERT_ERROR;
        session.setAttribute(alertType, true);
        session.setAttribute(Attributes.ALERT_MESSAGE, messageKey);
    }

}
