package com.example.starbattle;

import com.sun.jdi.InvalidTypeException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class BurnFuelCommand implements Command {

    private final Fuel fuel;

    @SneakyThrows
    @Override
    public void execute() {
        Object volume = fuel.fuelVolume();
        Object expense = fuel.fuelExpense();

        if (volume instanceof Number && expense instanceof Number) {
            fuel.saveFuelVolume(((Number) volume).doubleValue() - ((Number) expense).doubleValue());
        } else {
            throw new InvalidTypeException("Invalid type for fuelVolume() or fuelExpense()");
        }
    }
}
