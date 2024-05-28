package com.example.hwpart6.command.log;

import com.example.hwpart6.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogErrorCommand implements Command {
    private static final Logger log = LoggerFactory.getLogger(LogErrorCommand.class);
    private final Command command;
    private final Exception exception;

    public LogErrorCommand(Command command, Exception exception) {
        this.command = command;
        this.exception = exception;
    }

    @Override
    public void execute() {
        log.error(
                "Error in operation: {}, verdict: {}",
                command.getClass().getName(),
                exception.getMessage());
    }
}
