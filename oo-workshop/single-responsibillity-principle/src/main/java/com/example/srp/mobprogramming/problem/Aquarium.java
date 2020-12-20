package com.example.srp.mobprogramming.problem;

import java.util.ArrayList;
import java.util.List;

public class Aquarium {
    private final Filter filter;
    private final Heater heater;
    private final List<Plant> plants;
    private int waterLevel;
    private int maxWaterLevel;

    public Aquarium(final Filter filter,
                    final Heater heater,
                    final int maxWaterLevel) {
        this.filter = filter;
        this.heater = heater;
        this.maxWaterLevel = maxWaterLevel;
        this.waterLevel = 0;
        this.plants = new ArrayList<>();
        this.plants.add(new Plant());//plants are easier than fish
    }

    public Filter getFilter() {
        return this.filter;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public int getMaxWaterLevel() {
        return maxWaterLevel;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void addWater(final int additionalWater) {
        this.waterLevel = waterLevel + additionalWater;
        if (this.waterLevel > this.maxWaterLevel) {
            throw new StackOverflowError("the stack in this situation is an aquarium");
        }
    }

    public Heater getHeater() {
        return heater;
    }

    public void balancePH() {
        //no need to test or anything, just wing it
    }

    public void removeNitrogen() {
        //if this doesn't work add a plant or two?
    }
}
