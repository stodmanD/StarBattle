package com.example.hwpart6.command.operations;


import com.example.hwpart6.command.Command;
import com.example.hwpart6.objects.UObject;

public class UpdateObjectPropertyCommand implements Command {
    private final UObject uObject;
    private final String key;
    private final Object newValue;

    public UpdateObjectPropertyCommand(UObject uObject, String key, Object newValue) {
        this.uObject = uObject;
        this.key = key;
        this.newValue = newValue;
    }

    @Override
    public void execute() {
        uObject.setProperty(key, newValue);
    }
}
