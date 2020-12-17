package com.example.mobprogramming.problem;

public class FoodOrder {
    Burger burger = new Burger();
    Milkshake milkshake = new Milkshake();

    public void getOrder(){
        burger.makeBurger();
        milkshake.makeMilkshake();
    }
}
