package com.example.mobprogramming.problem;

public class Move {
    boolean batteryCharged;
    Car car = new Car();
    Bus bus = new Bus();
    Bicycle bicycle = new Bicycle();
    Skateboard skateboard = new Skateboard();

    public void startMoving(){
        car.startEngine(batteryCharged);
        bus.startEngine(batteryCharged);
        bicycle.startEngine(batteryCharged); // there's no engine to start!
        skateboard.startEngine(batteryCharged); // there's no engine to start!
    }
}
