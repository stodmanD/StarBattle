package com.example.starbattle;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RotateCommand implements Command {
    private final Rotatable rotatable;

    @Override
    public void execute() {
        int newDirection = (this.rotatable.getDirection() + this.rotatable.getAngularVelocity()) % this.rotatable.getDirectionsNumber();
        rotatable.setDirection(newDirection);
    }
}
