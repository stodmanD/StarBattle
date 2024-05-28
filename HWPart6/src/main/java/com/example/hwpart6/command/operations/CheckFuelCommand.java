package com.example.hwpart6.command.operations;


import com.example.hwpart6.command.Command;
import com.example.hwpart6.exception.exceptions.CheckFuelCommandException;
import com.example.hwpart6.exception.exceptions.CommandException;
import com.example.hwpart6.ioc.IoC;
import com.example.hwpart6.objects.UObject;
import com.example.hwpart6.operations.Fuelable;

public class CheckFuelCommand implements Command {
    private final Fuelable fuelableAdapter;

    public CheckFuelCommand(UObject fuelableObject) {
        this.fuelableAdapter = IoC.resolve("FuelableAdapter", fuelableObject);
    }

    @Override
    public void execute() {
        try {
            double fuel = fuelableAdapter.getFuel()
                    .orElseThrow(() -> new CheckFuelCommandException("no fuel found"));
            double fuelConsumption = fuelableAdapter.getFuelConsumption()
                    .orElseThrow(() -> new CheckFuelCommandException("no fuel consumption found"));
            if (fuel < fuelConsumption) {
                throw new CheckFuelCommandException("not enough fuel");
            }
        } catch (Exception ex) {
            throw new CommandException("Error when try to check fuel. verdict: " + ex.getMessage());
        }
    }
}
