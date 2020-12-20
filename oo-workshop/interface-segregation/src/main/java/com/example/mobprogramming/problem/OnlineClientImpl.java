package com.example.mobprogramming.problem;

public class OnlineClientImpl implements RestaurantInterface {
    public String name;
    public String food;
    public String price;

    public OnlineClientImpl(String name, String food, String price){
        this.name = name;
        this.food = food;
        this.price = price;
    }

    @Override
    public String acceptOnlineOrder() {
        String message = "Online order from " + this.name + " for " + this.food;
        return message;
    }

    @Override
    public String payOnline() {
        String message = "Online payment for $" + this.price + " processed";
        return message;
    }

    @Override
    public String walkInCustomerOrder() {
        //not applicable for online order
        throw new UnsupportedOperationException();
    }

    @Override
    public String payInPerson() {
        //not applicable for online order
        throw new UnsupportedOperationException();
    }
}
