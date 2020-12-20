package com.example.mobprogramming.problem;

public class TransportationObject {
    double speed;
    Engine engine;
    boolean batteryCharged;
    boolean forceApplied;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

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

    public boolean isForceApplied() {
        return forceApplied;
    }

    public void setForceApplied(boolean forceApplied) {
        this.forceApplied = forceApplied;
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

    public String applyForce(boolean forceApplied) {
        String message;
        if(forceApplied == true){
            message = "Start moving...";
        }
        else{
            message = "Can't move. Apply more force.";
        }
        return message;
    }
}
