package com.example.liskovsubstitution.mobprogramming.problem;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TransportationObjectTest {
    @Test
    public void whenBatteryChargedIsTrueThenMessageContainsStartingEngine() {
        //given
        Car car = new Car();
        Bus bus = new Bus();

        //when
        String whencar = car.startEngine(true);
        String whenbus = bus.startEngine(true);

        //then
        assertTrue(whencar.contains("Starting engine"));
        assertTrue(whenbus.contains("Starting engine"));
    }

    @Test
    public void whenBatteryChargedIsFalseThenMessageContainsChargeBattery() {
        //given
        Car car = new Car();
        Bus bus = new Bus();

        //when
        String whencar = car.startEngine(false);
        String whenbus = bus.startEngine(false);

        //then
        assertTrue(whencar.contains("Charge battery"));
        assertTrue(whenbus.contains("Charge battery"));
    }

    @Test
    public void whenForceAppliedIsTrueThenMessageContainsStartMoving() {
        //given
        Bicycle bicycle = new Bicycle();
        Skateboard skateboard = new Skateboard();

        //when
        String whenbicycle = bicycle.applyForce(true);
        String whenskateboard = skateboard.applyForce(true);

        //then
        assertTrue(whenbicycle.contains("Start moving"));
        assertTrue(whenskateboard.contains("Start moving"));
    }

    @Test
    public void whenForceAppliedIsFalseThenMessageContainsApplyMoreForce() {
        //given
        Bicycle bicycle = new Bicycle();
        Skateboard skateboard = new Skateboard();

        //when
        String whenbicycle = bicycle.applyForce(false);
        String whenskateboard = skateboard.applyForce(false);

        //then
        assertTrue(whenbicycle.contains("Apply more force"));
        assertTrue(whenskateboard.contains("Apply more force"));
    }
}
