package com.example.hwpart6.command.operations;


import com.example.hwpart6.command.Command;
import com.example.hwpart6.command.operations.move.StopMovableCommand;
import com.example.hwpart6.operations.Movable;

public class StopCommand implements Command {
    private final Command command;

    public StopCommand(Movable movableAdapter) {
        this.command = new StopMovableCommand(movableAdapter);
    }

    @Override
    public void execute() {
        command.execute();
    }
}
