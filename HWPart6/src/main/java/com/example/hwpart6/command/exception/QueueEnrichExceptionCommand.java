package com.example.hwpart6.command.exception;


import com.example.hwpart6.command.Command;
import com.example.hwpart6.command.queue.AddElementInQueueCommand;

public class QueueEnrichExceptionCommand implements Command {
    private final Command command;

    public QueueEnrichExceptionCommand(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        new AddElementInQueueCommand(command).execute();
    }
}
