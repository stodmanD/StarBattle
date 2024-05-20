package com.example.starbattle;

import com.example.starbattle.Exeptions.CommandException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayList;
@RequiredArgsConstructor
public class MacroCommand implements Command{


   private final ArrayList <Command> commands;

@SneakyThrows
    @Override
    public void execute() {
        try {
            for (Command command : commands) {
                command.execute();
            }
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }
}
