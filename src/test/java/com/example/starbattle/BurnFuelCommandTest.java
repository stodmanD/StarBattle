package com.example.starbattle;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import com.sun.jdi.InvalidTypeException;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import org.junit.jupiter.api.Test;
public class BurnFuelCommandTest {

    @Test
    void testExecute() {
        Fuel fuel = mock(Fuel.class);
        when(fuel.fuelVolume()).thenReturn(100.0);
        when(fuel.fuelExpense()).thenReturn(10.0);

        BurnFuelCommand command = new BurnFuelCommand(fuel);

        command.execute();

        verify(fuel, times(1)).saveFuelVolume(90.0);
    }

    @Test
    void testExecute_invalidType() {
        Fuel fuel = mock(Fuel.class);
        when(fuel.fuelVolume()).thenReturn("100.0");
        when(fuel.fuelExpense()).thenReturn("10.0");

        BurnFuelCommand command = new BurnFuelCommand(fuel);

        assertThrows(InvalidTypeException.class, () -> {
            command.execute();
        });
    }
}
