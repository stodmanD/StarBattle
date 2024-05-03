package com.example.starbattle;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;


public class MoveTest {

    @Mock
    private Vector positionMock;

    @Mock
    private Vector velocityMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void setPositionTest() {

        when(positionMock.getCoords()).thenReturn(new int[]{12, 5});
        when(velocityMock.getCoords()).thenReturn(new int[]{-7, 3});

        MoveImpl move = new MoveImpl(positionMock, velocityMock);

        Vector newPosition = move.setPosition(velocityMock);

        assertArrayEquals(new int[]{5, 8}, newPosition.getCoords());
    }

    @Test
    public void testThrowErrorWhenGetPosition() {

        when(positionMock.getCoords()).thenThrow(new RuntimeException());

        MoveImpl move = new MoveImpl(positionMock, velocityMock);

        assertThrows(RuntimeException.class, () -> move.setPosition(velocityMock));

    }

    @Test
    public void testThrowErrorWhenGetVelocity() {
        when(velocityMock.getCoords()).thenThrow(new RuntimeException());

        MoveImpl move = new MoveImpl(positionMock, velocityMock);

        assertThrows(RuntimeException.class, () -> move.setPosition(velocityMock));

    }

    @Test
    public void testThrowErrorWhenSetPosition() {

        when(positionMock.getCoords()).thenReturn(new int[]{12, 5});
        when(velocityMock.getCoords()).thenReturn(new int[]{-7, 3});

        MoveImpl move = new MoveImpl(positionMock, velocityMock);

        Mockito.when(move.setPosition(velocityMock)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> move.setPosition(velocityMock));

    }
}
