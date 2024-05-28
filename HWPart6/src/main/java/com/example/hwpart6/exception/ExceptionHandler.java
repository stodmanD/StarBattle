package com.example.hwpart6.exception;


import com.example.hwpart6.command.Command;

public class ExceptionHandler {
    private ExceptionHandler() {
        throw new IllegalStateException("Exception handler class");
    }

    public static Command handle(Exception exception, Command command) {
        return ExceptionHandlerCommandFactory.getInstance(command, exception);
    }
}
