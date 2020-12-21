package com.example.srp.mobprogramming.problem;

import static com.example.srp.mobprogramming.problem.ConstantsAreNotHardCoding.*;

/**
 * This is our starting point, and apparently our customers have some complaints
 * we're going to have to change how we service aquariums, or we'll lose business:
 *
 *  * first, we're going to need to stop worrying about fish being chilly, they prefer a breezy 70
 *    * cold-blooded doesn't mean they're cold
 *  * second, we stop guestimating with the chemical balancing and test our PH level first
 *  * third, we're going to switch to battery operated filters and heaters
 *    * we bought a whole bunch of battery operated filters and heaters on accident
 *    * no one asked for this, but we're going to sneak it in anyway
 *  * fourth, we need to stop over-trimming plants, we ran out of `GreenThumb`s a while ago
 *  * fifth, my notes are getting pretty tear stained, so let's just do the first 4 things
 *    * there is a nice way to ask for changes to our AquariumService, sheesh
 *
 */
public class AquariumService {

    private final Aquarium aquarium;

    public AquariumService(final Aquarium aquarium){
        this.aquarium = aquarium;
    }

    public void serviceAquarium(){
        turnOffFilter();
        topOffWater();
        trimPlants();
        setTemperature(OPTIMAL_TEMP_F);
        balanceChemicals();
        turnOnFilter();
    }

    private void turnOnFilter() {
        aquarium.getFilter().getPowerCord().plug();
    }

    private void balanceChemicals() {
        aquarium.balancePH();
        aquarium.removeNitrogen();
    }

    private void turnOffFilter() {
        aquarium.getFilter().getPowerCord().unplug();
    }

    private void topOffWater(){
        int currentWaterLevel = aquarium.getWaterLevel();
        int desiredWaterLevel = aquarium.getMaxWaterLevel();

        aquarium.addWater(desiredWaterLevel - currentWaterLevel);
    }

    private void trimPlants(){
        aquarium.getPlants().forEach(Plant::trim);
    }

    private void setTemperature(int newTemp) {
        aquarium.getHeater().setTemperature(newTemp);
        try {
            Thread.sleep(ONE_SECOND);
        } catch (Exception ignored){
            //being impatient isn't THAT exceptional
        }
        //they looked cold
        aquarium.getHeater().setTemperature(newTemp + LIL_BIT);
    }

}
