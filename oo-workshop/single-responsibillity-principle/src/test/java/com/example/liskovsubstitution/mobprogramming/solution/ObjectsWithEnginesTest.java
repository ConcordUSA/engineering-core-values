package com.example.liskovsubstitution.mobprogramming.solution;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ObjectsWithEnginesTest {
    @Test
    void whenBatteryChargedIsTrueThenMessageContainsStartingEngine(){
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
    void whenBatteryChargedIsFalseThenMessageContainsChargeBattery(){
        //given
        Car car = new Car();
        Bus bus = new Bus();

        //when
        String whencar = car.startEngine(true);
        String whenbus = bus.startEngine(true);

        //then
        assertTrue(whencar.contains("Charge battery"));
        assertTrue(whenbus.contains("Charge battery"));
    }
}
