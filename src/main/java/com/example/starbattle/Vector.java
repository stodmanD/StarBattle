package com.example.starbattle;

import lombok.Data;

@Data
public class Vector {
    private int[] coords;

    public static Vector newCoords(Vector x, Vector y) {
        int[] xp = x.getCoords();
        int[] yp = y.getCoords();
        return new Vector(xp[0] + yp[0], xp[1] + yp[1]);
    }

    public Vector(int x, int y) {
        this.coords = new int[]{x, y};
    }

    public int[] getCoords() {
        return this.coords;
    }
}
