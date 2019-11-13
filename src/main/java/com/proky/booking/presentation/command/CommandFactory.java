package com.proky.booking.presentation.command;

import com.proky.booking.presentation.command.fragment.SignInFragmentCommand;
import com.proky.booking.presentation.command.fragment.SignUpFragmentCommand;
import com.proky.booking.presentation.command.fragment.TicketBookingFragmentCommand;
import com.proky.booking.util.constans.Commands;
import com.proky.booking.util.constans.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


public class CommandFactory {
    private static final Logger log = LogManager.getLogger(CommandFactory.class);

    private static HashMap<String, ICommand> commandHashMap = new HashMap<>();

    static {
        commandHashMap.put(Commands.GET_SIGN_UP_FRAGMENT, new SignUpFragmentCommand());
        commandHashMap.put(Commands.GET_SIGN_IN_FRAGMENT, new SignInFragmentCommand());
        commandHashMap.put(Commands.SIGN_UP, new SignUpCommand());
        commandHashMap.put(Commands.SIGN_IN, new SignInCommand());
        commandHashMap.put(Commands.FIND_TRAIN, new FindTrainsCommand());
        commandHashMap.put(Commands.GET_TICKET_BOOKING_FRAGMENT, new TicketBookingFragmentCommand());
        commandHashMap.put(Commands.CHANGE_LANGUAGE, new ChangeLanguageCommand());
    }

    public static ICommand getCommand(HttpServletRequest request) {
        final String clientCommand = request.getParameter(Parameters.COMMAND);
        log.info("======= COMMAND: {} ======= ", clientCommand);

        boolean isCommandInvalid = clientCommand == null || clientCommand.isEmpty() || commandHashMap.get(clientCommand) == null;
        return isCommandInvalid ? new EmptyCommand() : commandHashMap.get(clientCommand);
    }
}
