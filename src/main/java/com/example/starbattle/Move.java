package com.example.starbattle;

public interface Move {
    Vector getPosition();
    Vector getVelocity();
    Vector setPosition(Vector newValue);
}
