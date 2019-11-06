package com.proky.booking.presentation.command;

import com.proky.booking.persistence.entity.User;
import com.proky.booking.service.ValidationService;
import com.proky.booking.util.PasswordEncryptor;
import com.proky.booking.util.command.HttpRequestDataBinder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SignUpCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(SignUpCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("user sign up");
        final HttpRequestDataBinder parametersExctractor = HttpRequestDataBinder.getInstance();
        final User user = parametersExctractor.bindToEntity(request, User.class);
        System.out.println(user);

        // validate
        final ValidationService validationService = ValidationService.getInstance();
        validationService.validate(user);

        // pass to service
        final PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();
        final String encryptedPassword = passwordEncryptor.encryptPassword(user.getPassword());

        System.out.println(encryptedPassword);

        // set attributes
        // return page

        return null;
    }
}
