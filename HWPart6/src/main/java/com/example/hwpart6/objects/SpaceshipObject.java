package com.example.hwpart6.objects;

import java.util.HashMap;

public class SpaceshipObject implements UObject {
    private static SpaceshipObject instance;
    private final HashMap<String, Object> spaceship;

    public SpaceshipObject(){
        this.spaceship = new HashMap<>();
    }

    public static synchronized SpaceshipObject getInstance() {
        if (instance == null) {instance = new SpaceshipObject();}
        return instance;
    }

    @Override
    public Object getProperty(String key) {
        return spaceship.get(key);
    }

    @Override
    public void setProperty(String key, Object newValue) {
        spaceship.put(key, newValue);
    }
}
