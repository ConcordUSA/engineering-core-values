package com.example.srp.mobprogramming.problem;

public class Heater {
    private final PowerCord powerCord;
    private int temperature;

    public Heater(final PowerCord powerCord) {
        this.powerCord = powerCord;
        this.temperature = 212;//for some reason boiling water at sea level is 212
    }

    public PowerCord getPowerCord() {
        return this.powerCord;
    }

    public boolean isHeating() {
        return powerCord.isPowered();
    }

    public void setTemperature(int newTemp) {
        this.temperature = newTemp;
    }

    public int getTemperature() {
        return temperature;
    }
}
