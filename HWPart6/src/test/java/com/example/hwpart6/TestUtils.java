package com.example.hwpart6;


import com.example.hwpart6.objects.SpaceshipObject;

public class TestUtils {
    public static void clearSpaceshipObject() {
        var spaceshipObject = SpaceshipObject.getInstance();
        spaceshipObject.setProperty("Position", null);
        spaceshipObject.setProperty("VelocityVector", null);
        spaceshipObject.setProperty("Velocity", null);
        spaceshipObject.setProperty("Direction", null);
        spaceshipObject.setProperty("DirectionsNumber", null);
    }
}
