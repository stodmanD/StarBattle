package com.example.starbattle;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MoveCommandTest {

    @Mock
    private Movable movable;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("Для объекта, находящегося в точке (12, 5) и движущегося со скоростью (-7, 3) движение меняет положение объекта на (5, 8)")
    @Test
    void setPositionTest() {
        when(movable.getPosition()).thenReturn(new Vector(12, 5));
        when(movable.getVelocity()).thenReturn(new Vector(-7, 3));
        MoveCommand moveCommand = new MoveCommand(movable);
        moveCommand.execute();
        Vector newPosition = new Vector(5, 8);
        verify(movable, times(1)).setPosition(newPosition);
    }

    @DisplayName("Попытка сдвинуть объект, у которого невозможно прочитать положение в пространстве, приводит к ошибке")
    @Test
    public void testThrowErrorWhenGetPosition() {
        when(movable.getPosition()).thenReturn(null);
        when(movable.getVelocity()).thenReturn(new Vector(2, 2));
        MoveCommand moveCommand = new MoveCommand(movable);
        assertThrows(NullPointerException.class, () -> {
            moveCommand.execute();
        });
    }

    @DisplayName("Попытка сдвинуть объект, у которого невозможно прочитать значение мгновенной скорости, приводит к ошибке")
    @Test
    public void testThrowErrorWhenGetVelocity() {
        when(movable.getPosition()).thenReturn(new Vector(1, 2));
        when(movable.getVelocity()).thenReturn(null);
        MoveCommand moveCommand = new MoveCommand(movable);
        assertThrows(NullPointerException.class, () -> {
            moveCommand.execute();
        });
    }

    @DisplayName("Попытка сдвинуть объект, у которого невозможно изменить положение в пространстве, приводит к ошибке")
    @Test
    public void testThrowErrorWhenSetPosition() {
        when(movable.getPosition()).thenReturn(new Vector(1, 2));
        when(movable.getVelocity()).thenReturn(new Vector(2, 2));
        doThrow(new NullPointerException()).when(movable).setPosition(any(Vector.class));
        MoveCommand moveCommand = new MoveCommand(movable);
        assertThrows(NullPointerException.class, () -> {
            moveCommand.execute();
        });
    }
}
