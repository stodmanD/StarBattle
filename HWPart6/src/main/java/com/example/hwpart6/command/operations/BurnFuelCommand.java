package com.example.hwpart6.command.operations;


import com.example.hwpart6.command.Command;
import com.example.hwpart6.exception.exceptions.CheckFuelCommandException;
import com.example.hwpart6.exception.exceptions.CommandException;
import com.example.hwpart6.ioc.IoC;
import com.example.hwpart6.objects.UObject;
import com.example.hwpart6.operations.Fuelable;

public class BurnFuelCommand implements Command {
    private final Fuelable fuelableAdapter;

    public BurnFuelCommand(UObject fuelableObject) {
        this.fuelableAdapter = IoC.resolve("FuelableAdapter", fuelableObject);
    }

    @Override
    public void execute() {
        try {
            double fuel = fuelableAdapter.getFuel()
                    .orElseThrow(() -> new CheckFuelCommandException("no fuel found"));
            double fuelConsumption = fuelableAdapter.getFuelConsumption()
                    .orElseThrow(() -> new CheckFuelCommandException("no fuel consumption found"));
            fuelableAdapter.setFuel(fuel - fuelConsumption);
        } catch (Exception ex) {
            throw new CommandException("Error when try to burn fuel. verdict: " + ex.getMessage());
        }
    }
}
