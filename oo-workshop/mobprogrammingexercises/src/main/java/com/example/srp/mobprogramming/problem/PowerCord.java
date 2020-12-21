package com.example.srp.mobprogramming.problem;

public class PowerCord {
    private boolean powered = false;

    public void unplug() {
        powered = false;
    }

    public void plug() {
        powered = true;
    }

    public boolean isPowered() {
        return powered;
    }
}
