package com.proky.booking.presentation.command;

import com.proky.booking.presentation.command.impl.EmptyCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


public class CommandFactory {
    private static final Logger log = LogManager.getLogger(CommandFactory.class);

    private static HashMap<String, ICommand> commandHashMap = new HashMap<>();

    static {

    }

    public static ICommand getCommand(HttpServletRequest request) {
        ICommand command;
        final String clientCommand = request.getParameter("command");
        log.info("command - {}", clientCommand);

        if (clientCommand == null || clientCommand.isEmpty() || commandHashMap.get(clientCommand) == null) {
            command = new EmptyCommand();
        } else {
            command = commandHashMap.get(clientCommand);
        }

        return command;
    }
}
