package com.example.starbattle;

import com.example.starbattle.Exeptions.CommandException;
import com.sun.jdi.InvalidTypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CheckFuelCommandTest {

    @Test
    public void testSufficientFuel() {
        Fuel fuel = mock(Fuel.class);
        when(fuel.fuelVolume()).thenReturn(100.0);
        when(fuel.fuelExpense()).thenReturn(10.0);

        CheckFuelCommand command = new CheckFuelCommand(fuel);

        command.execute();
    }

    @Test
    public void testInsufficientFuel() {
        Fuel fuel = mock(Fuel.class);
        when(fuel.fuelVolume()).thenReturn(5.0);
        when(fuel.fuelExpense()).thenReturn(10.0);

        CheckFuelCommand command = new CheckFuelCommand(fuel);

        assertThrows(CommandException.class, () -> {
            command.execute();
        });
    }

    @Test
    public void testInvalidFuelType() {
        Fuel fuel = mock(Fuel.class);
        when(fuel.fuelVolume()).thenReturn("invalid");
        when(fuel.fuelExpense()).thenReturn(10.0);

        CheckFuelCommand command = new CheckFuelCommand(fuel);

        assertThrows(InvalidTypeException.class, () -> {
            command.execute();
        });
    }

}
