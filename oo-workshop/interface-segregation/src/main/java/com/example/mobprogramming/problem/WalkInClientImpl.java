package com.example.mobprogramming.problem;

public class WalkInClientImpl implements RestaurantInterface{
    public String name;
    public String food;
    public int price;

    public WalkInClientImpl(String name, String food, int price){
        this.name = name;
        this.food = food;
        this.price = price;
    }

    @Override
    public String acceptOnlineOrder() {
        //not applicable for walk-in client
        throw new UnsupportedOperationException();
    }
    @Override
    public String payOnline() {
        //not applicable for walk-in client
        throw new UnsupportedOperationException();
    }
    @Override
    public String walkInCustomerOrder() {
        String message = "Walk-in order from " + this.name + " for " + this.food;
        return message;
    }
    @Override
    public String payInPerson() {
        String message = "In-person payment for $" + this.price + " processed";
        return message;
    }
}
