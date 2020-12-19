package com.example.mobprogramming.solution;

public class Move {
    boolean batteryCharged;
    boolean forceApplied;
    Car car = new Car();
    Bus bus = new Bus();
    Bicycle bicycle = new Bicycle();
    Skateboard skateboard = new Skateboard();

    public void startMoving(){
        car.startEngine(batteryCharged);
        bus.startEngine(batteryCharged);
        bicycle.applyForce(forceApplied);
        skateboard.applyForce(forceApplied);
    }
}
