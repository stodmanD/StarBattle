package com.example.hwpart6.command.operations.move;


import com.example.hwpart6.command.Command;
import com.example.hwpart6.exception.ExceptionHandler;
import com.example.hwpart6.exception.exceptions.CheckFuelCommandException;
import com.example.hwpart6.exception.exceptions.CommandException;
import com.example.hwpart6.ioc.IoC;
import com.example.hwpart6.objects.UObject;
import com.example.hwpart6.operations.Movable;

public class MoveLinearCommand implements Command {
    private final UObject movableObject;
    private final Movable movableAdapter;

    public MoveLinearCommand(UObject movableObject) {
        this.movableObject = movableObject;
        this.movableAdapter = IoC.resolve("MovableAdapter", movableObject);
    }

    @Override
    public void execute() {
        try {
            var velocityVector = getLinearVelocityVector();
            new MoveCommand(movableObject, velocityVector).execute();
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
            throw new CommandException("error when linear move. verdict: " + ex.getMessage());
        }
    }

    private double[] getLinearVelocityVector() {
        var velocity = movableAdapter.getVelocity()
                .orElseThrow(() -> new CheckFuelCommandException("no object velocity found"));
        return new double[]{velocity, 0};
    }
}
