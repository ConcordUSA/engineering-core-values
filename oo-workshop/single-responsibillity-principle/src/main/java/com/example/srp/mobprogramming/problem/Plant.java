package com.example.srp.mobprogramming.problem;

import static com.example.srp.mobprogramming.problem.ConstantsAreNotHardCoding.FIVE_DAYS;
import static com.example.srp.mobprogramming.problem.ConstantsAreNotHardCoding.THREE_DAYS;

public class Plant {

    private long lastTrimmed;

    public Plant() {
        //new plants were always trimmed 5 days ago
        this.lastTrimmed = System.currentTimeMillis() - FIVE_DAYS;
    }

    public void trim() {
        if (System.currentTimeMillis() - lastTrimmed < THREE_DAYS) {
            throw new PlantDeathException();
        }
        lastTrimmed = System.currentTimeMillis();
    }

}
