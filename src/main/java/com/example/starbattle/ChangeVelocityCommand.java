package com.example.starbattle;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChangeVelocityCommand implements Command{

    private final Movable movable;
    private final Rotatable rotatable;
    @Override
    public void execute() {
        double x = movable.getPosition().getX()+movable.getVelocity().getX()* Math.cos(rotatable.getDirection()*Math.PI / 180)-movable.getVelocity().getY()*Math.sin(rotatable.getDirection()*Math.PI / 180);
        double y = movable.getPosition().getY()+movable.getVelocity().getX()*Math.sin(rotatable.getDirection()*Math.PI / 180)+movable.getVelocity().getY()*Math.cos(rotatable.getDirection()*Math.PI / 180);

        double vx = x - movable.getPosition().getX();
        double vy = y - movable.getPosition().getY();
        movable.setVelocity(new Vector(vx, vy));
        movable.setPosition(new Vector(x, y));

    }
}
