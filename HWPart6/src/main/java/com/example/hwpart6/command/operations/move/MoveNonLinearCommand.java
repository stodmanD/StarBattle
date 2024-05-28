package com.example.hwpart6.command.operations.move;


import com.example.hwpart6.command.Command;
import com.example.hwpart6.command.operations.macro.RotateThenChangeVelocityVectorMacroCommand;
import com.example.hwpart6.exception.ExceptionHandler;
import com.example.hwpart6.ioc.IoC;
import com.example.hwpart6.objects.UObject;
import com.example.hwpart6.operations.Rotatable;

public class MoveNonLinearCommand implements Command {
    private final UObject movableAndRotatableObject;
    private final Rotatable rotatableAdapter;

    public MoveNonLinearCommand(UObject movableAndRotatableObject) {
        this.movableAndRotatableObject = movableAndRotatableObject;
        this.rotatableAdapter = IoC.resolve("RotatableAdapter", movableAndRotatableObject);
    }

    @Override
    public void execute() {
        try {
            var velocityVector = getVelocityVector();
            new MoveCommand(movableAndRotatableObject, velocityVector).execute();
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
        }
    }

    private double[] getVelocityVector() {
        try {
            var velocityVectorOptional = rotatableAdapter.getVelocityVector();
            if (velocityVectorOptional.isEmpty()) {
                new RotateThenChangeVelocityVectorMacroCommand(movableAndRotatableObject).execute();
                return rotatableAdapter
                        .getVelocityVector()
                        .orElseThrow(IllegalStateException::new);
            } else {
                return velocityVectorOptional.get();
            }
        } catch (Exception ex) {
            throw new IllegalStateException("error when try to rotate");
        }
    }
}
