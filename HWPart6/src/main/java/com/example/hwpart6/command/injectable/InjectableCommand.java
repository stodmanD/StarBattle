package com.example.hwpart6.command.injectable;


import com.example.hwpart6.command.Command;

public class InjectableCommand implements Injectable, Command {
    private Command commandImpl;

    public InjectableCommand() {
    }

    public InjectableCommand(Command commandImpl) {
        this.commandImpl = commandImpl;
    }

    @Override
    public void execute() {
        commandImpl.execute();
    }

    @Override
    public void inject(Command command) {
        commandImpl = command;
    }
}
