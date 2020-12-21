package com.example.srp.mobprogramming.problem;

import static com.example.srp.mobprogramming.problem.ConstantsAreNotHardCoding.PLANT_DEATH_MESSAGE;

class PlantDeathException extends RuntimeException {
    public PlantDeathException() {
        super(PLANT_DEATH_MESSAGE);
    }
}
