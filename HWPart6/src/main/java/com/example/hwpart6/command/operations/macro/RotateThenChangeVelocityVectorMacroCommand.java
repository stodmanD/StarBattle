package com.example.hwpart6.command.operations.macro;


import com.example.hwpart6.command.Command;
import com.example.hwpart6.command.MacroCommand;
import com.example.hwpart6.command.operations.ChangeVelocityVectorCommand;
import com.example.hwpart6.command.operations.RotateCommand;
import com.example.hwpart6.exception.exceptions.CommandException;
import com.example.hwpart6.objects.UObject;

public class RotateThenChangeVelocityVectorMacroCommand implements Command {
    private final Command[] commands;

    public RotateThenChangeVelocityVectorMacroCommand(UObject rotatableObject) {
        this.commands = new Command[] {
                new RotateCommand(rotatableObject),
                new ChangeVelocityVectorCommand(rotatableObject)
        };
    }

    @Override
    public void execute() {
        try {
            new MacroCommand(commands).execute();
        } catch (Exception ex) {
            throw new CommandException("error when try to rotate with changing velocity vector");
        }
    }
}
