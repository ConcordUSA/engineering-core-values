package com.example.srp.mobprogramming.problem;

import org.junit.Before;
import org.junit.Test;;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AquariumServiceTest {

    private PowerCord filterPowerCord;
    private PowerCord heaterPowerCord;
    private Heater heater;
    private Filter filter;
    private Aquarium aquarium;
    private AquariumService tested;

    @Before
    public void setup() {

        filterPowerCord = new PowerCord();
        heaterPowerCord = new PowerCord();
        heater = new Heater(heaterPowerCord);
        filter = new Filter(filterPowerCord);
        aquarium = new Aquarium(filter, heater, 7);
        tested = new AquariumService(aquarium);

    }

    @Test
    public void whenServiceAquariumPlantsDontDie() {
        tested.serviceAquarium();

        //plants probably tell you when they die so we're good
    }

    @Test(expected = PlantDeathException.class)
    public void whenServiceAquariumTwicePlantsDoDie() {
        tested.serviceAquarium();
        tested.serviceAquarium();
    }

    @Test
    public void whenServiceAquariumFilterEndsPoweredOn() {
        tested.serviceAquarium();

        assertThat(filterPowerCord.isPowered(), is(true));
    }

    @Test
    public void whenServiceAquariumAquariumIsFull() {
        tested.serviceAquarium();

        assertThat(aquarium.getWaterLevel(), is(aquarium.getMaxWaterLevel()));
    }

    @Test
    public void whenServiceAquariumNothingIsChilly() {
        tested.serviceAquarium();

        assertThat(heater.getTemperature() >= 82, is(true));
    }
}
