package com.example.hwpart6.command.operations.move;

import com.example.hwpart6.command.Command;
import com.example.hwpart6.command.EmptyCommand;
import com.example.hwpart6.exception.ExceptionHandler;
import com.example.hwpart6.exception.exceptions.CommandException;
import com.example.hwpart6.operations.Movable;

public class StopMovableCommand implements Command {
    private final Movable movableAdapter;

    public StopMovableCommand(Movable movableAdapter) {
        this.movableAdapter = movableAdapter;
    }

    @Override
    public void execute() {
        try {
            movableAdapter.getMovement()
                    .orElseThrow(() -> new CommandException("Error when finish movable"))
                    .inject(new EmptyCommand());
            movableAdapter.setMovement(null);
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
        }
    }
}
