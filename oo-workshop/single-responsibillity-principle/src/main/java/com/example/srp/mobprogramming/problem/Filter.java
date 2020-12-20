package com.example.srp.mobprogramming.problem;

public class Filter {
    private final PowerCord powerCord;

    public Filter(final PowerCord powerCord) {
        this.powerCord = powerCord;
    }

    public PowerCord getPowerCord() {
        return this.powerCord;
    }

    public boolean isFiltering() {
        return powerCord.isPowered();
    }
}
