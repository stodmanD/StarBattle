package com.example.starbattle;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MoveCommand implements Command {

    private final Movable movable;

    @Override
    public void execute() {
        movable.setPosition(new Vector(movable.getPosition().getX() + movable.getVelocity().getX(),
                movable.getPosition().getY() + movable.getVelocity().getY()));
    }
}
