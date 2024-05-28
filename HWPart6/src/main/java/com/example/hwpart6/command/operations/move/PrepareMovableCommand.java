package com.example.hwpart6.command.operations.move;


import com.example.hwpart6.command.Command;
import com.example.hwpart6.command.MacroCommand;
import com.example.hwpart6.command.injectable.Injectable;
import com.example.hwpart6.command.injectable.InjectableCommand;
import com.example.hwpart6.command.queue.AddElementInQueueCommand;
import com.example.hwpart6.ioc.IoC;
import com.example.hwpart6.objects.UObject;
import com.example.hwpart6.operations.Movable;

public class PrepareMovableCommand implements Command {
    private final Movable movableAdapter;
    private final Injectable injectable;
    private final MacroCommand macroCommand;

    public PrepareMovableCommand(UObject movableObject, Command moveCommand) {
        this.movableAdapter = IoC.resolve("MovableAdapter", movableObject);
        this.injectable = new InjectableCommand();
        var repeatableCommand = new AddElementInQueueCommand((Command) injectable);
        this.macroCommand = new MacroCommand(new Command[] {
                moveCommand, repeatableCommand
        });
    }

    @Override
    public void execute() {
        injectable.inject(macroCommand);
        movableAdapter.setMovement(injectable);
    }
}
