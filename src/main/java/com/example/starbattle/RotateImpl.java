package com.example.starbattle;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RotateImpl {
    private final Rotate rotate;

    public void execute() {
        int newDirection = (this.rotate.getDirection() + this.rotate.getAngularVelocity()) % this.rotate.getDirectionsNumber();
        this.rotate.setDirection(newDirection);
    }
}
