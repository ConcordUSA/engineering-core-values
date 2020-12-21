package com.example.liskovsubstitution.solution;

public class ObjectsWithEngines {
    Engine engine;
    boolean batteryCharged;

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public boolean isBatteryCharged() {
        return batteryCharged;
    }

    public void setBatteryCharged(boolean batteryCharged) {
        this.batteryCharged = batteryCharged;
    }

    public String startEngine(boolean batteryCharged) {
        String message;
        if(batteryCharged == true){
            message = "Starting engine...";
        }
        else{
            message = "Can't start engine. Charge battery.";
        }
        return message;
    }
}
