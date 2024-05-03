package com.example.starbattle;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RotateImplTest {

    @Mock
    private Rotate rotate;

    @Test
    public void execute() {

        when(rotate.getDirection()).thenReturn(2);
        when(rotate.getAngularVelocity()).thenReturn(1);
        when(rotate.getDirectionsNumber()).thenReturn(4);

        RotateImpl rotateImpl = new RotateImpl(rotate);

        rotateImpl.execute();

        verify(rotate).setDirection(3);
    }
}
