package com.proky.booking.presentation.command;

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
        commandHashMap.put(Commands.SIGN_UP, new SignUpCommand());
        commandHashMap.put(Commands.SIGN_IN, new SignInCommand());
        commandHashMap.put(Commands.FIND_TRAIN, new FindTrainsCommand());
        commandHashMap.put(Commands.TRAIN_BOOKING, new TrainBookingCommand());
        commandHashMap.put(Commands.CHANGE_LANGUAGE, new ChangeLanguageCommand());
        commandHashMap.put(Commands.INVOICE, new InvoiceCommand());
        commandHashMap.put(Commands.MANAGE_USER, new ManageUserCommand());
    }

    public static ICommand getCommand(HttpServletRequest request) {
        final String clientCommand = request.getParameter(Parameters.COMMAND);
        log.info("======= COMMAND: {} ======= ", clientCommand);

        boolean isCommandInvalid = clientCommand == null || clientCommand.isEmpty() || commandHashMap.get(clientCommand) == null;
        return isCommandInvalid ? new EmptyCommand() : commandHashMap.get(clientCommand);
    }
}
