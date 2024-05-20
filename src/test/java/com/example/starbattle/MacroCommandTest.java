package com.example.starbattle;

import com.example.starbattle.Exeptions.CommandException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@Slf4j
public class MacroCommandTest {

    @Test
    @DisplayName("Реализовать команду движения по прямой с расходом топлива, используя команды с предыдущих шагов")
    public void testExecuteMove() {

        Vector newPosition = new Vector(3, 4);

        Movable movable = mock(Movable.class);

        when(movable.getPosition()).thenReturn(new Vector(1, 2));
        when(movable.getVelocity()).thenReturn(new Vector(2, 2));
        MoveCommand moveCommand = new MoveCommand(movable);


        Fuel fuel = mock(Fuel.class);
        when(fuel.fuelVolume()).thenReturn(100.0);
        when(fuel.fuelExpense()).thenReturn(10.0);

        CheckFuelCommand checkFuelCommand = new CheckFuelCommand(fuel);

        BurnFuelCommand burnFuelCommand = new BurnFuelCommand(fuel);

        ArrayList<Command> macroMoveComm = new ArrayList<>();
        macroMoveComm.add(moveCommand);
        macroMoveComm.add(checkFuelCommand);
        macroMoveComm.add(burnFuelCommand);

        MacroCommand macroCommand = new MacroCommand(macroMoveComm);
        macroCommand.execute();

        verify(movable, times(1)).setPosition(newPosition);
        verify(fuel, times(1)).saveFuelVolume(90.0);
    }


    @Test
    @DisplayName("вся последовательность команд приостанавливает свое выполнение, а макрокоманда выбрасывает CommandException")
    public void testMacroCommandException() {
        Movable movable = mock(Movable.class);
        when(movable.getPosition()).thenReturn(new Vector(1, 2));
        when(movable.getVelocity()).thenReturn(new Vector(2, 2));
        MoveCommand moveCommand = new MoveCommand(movable);

        Fuel fuel = mock(Fuel.class);
        when(fuel.fuelVolume()).thenReturn(1.0);
        when(fuel.fuelExpense()).thenReturn(10.0);

        CheckFuelCommand checkFuelCommand = new CheckFuelCommand(fuel);
        BurnFuelCommand burnFuelCommand = new BurnFuelCommand(fuel);

        ArrayList<Command> macroMoveComm = new ArrayList<>();
        macroMoveComm.add(moveCommand);
        macroMoveComm.add(checkFuelCommand);
        macroMoveComm.add(burnFuelCommand);

        MacroCommand macroCommand = new MacroCommand(macroMoveComm);

        assertThrows(CommandException.class, () -> {
            macroCommand.execute();
        });

        assertEquals(1.0, (double) fuel.fuelVolume(), 0.00001);
        assertEquals(new Vector(1,2), movable.getPosition());

    }

    @Test
    @DisplayName("Реализовать команду поворота, которая еще и меняет вектор мгновенной скорости, если есть")
    public void testExecuteRotate() {

        Vector position = new Vector(0, 0);
        Vector velocity = new Vector(0, 0);
        Movable move = move(position, velocity);

        int direction = 0;
        int angularVelocity = 90;
        int directionsNumber = 90;
        Rotatable rotate = rotate(direction, angularVelocity,directionsNumber);

        RotateCommand rotateCommand = new RotateCommand(rotate);
        ChangeVelocityCommand changeVelocityCommand = new ChangeVelocityCommand(move, rotate);

        ArrayList<Command> macroRotate = new ArrayList<>();

        macroRotate.add(rotateCommand);
        macroRotate.add(changeVelocityCommand);

        MacroCommand macroCommand = new MacroCommand(macroRotate);
        macroCommand.execute();

        //поворачиваем на 90 и проверяем Direction и Position

        assertEquals(90.0, rotate.getDirection(), 0.00001);

        assertEquals(0.0, move.getPosition().getX(), 0.00001);
        assertEquals(0.0, move.getPosition().getY(), 0.00001);

        macroCommand.execute();


    }

    private Movable move(Vector pos, Vector vel) {
        return new Movable() {

            private Vector position = pos;
            private Vector velocity = vel;

            @Override
            public Vector getPosition() {
                return position;
            }

            @Override
            public Vector getVelocity() {
                return velocity;
            }

            @Override
            public Vector setPosition(Vector newValue) {
                position = newValue;
                return newValue;
            }

            @Override
            public Vector setVelocity(Vector newValue) {
                velocity = newValue;
                return velocity;
            }
        };
    }

    private Rotatable rotate(int dir, int angular, int directionsNumber) {
        return new Rotatable() {
            private int direction = dir;

            @Override
            public int getDirection() {
                return direction;
            }

            @Override
            public int getAngularVelocity() {
                return angular;
            }

            @Override
            public void setDirection(int newDirection) {
                this.direction = newDirection;
            }

            @Override
            public int getDirectionsNumber() {
                return directionsNumber;
            }

        };
    }
}
