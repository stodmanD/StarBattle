package com.example.hwpart6.command.operations.move;


import com.example.hwpart6.command.Command;
import com.example.hwpart6.exception.ExceptionHandler;
import com.example.hwpart6.exception.exceptions.CommandException;
import com.example.hwpart6.ioc.IoC;
import com.example.hwpart6.objects.UObject;
import com.example.hwpart6.operations.Movable;

public class StartMovableCommand implements Command {
    private final Movable movableAdapter;

    public StartMovableCommand(UObject movableObject) {
        this.movableAdapter = IoC.resolve("MovableAdapter", movableObject);
    }

    @Override
    public void execute() {
        try {
            ((Command) movableAdapter.getMovement()
                    .orElseThrow(() -> new CommandException("Error when start movable")))
                    .execute();
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
        }
    }
}
