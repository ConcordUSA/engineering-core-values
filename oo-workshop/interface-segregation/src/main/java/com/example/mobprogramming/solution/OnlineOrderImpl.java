package com.example.mobprogramming.solution;

public class OnlineOrderImpl implements OrderInterface{
    public String name;
    public String food;

    public OnlineOrderImpl(String name, String food){
        this.name = name;
        this.food = food;
    }

    @Override
    public void placeOrder() {
        acceptOnlineOrder();
    }

    public String acceptOnlineOrder(){
        String message = "Online order from " + this.name + " for " + this.food;
        return message;
    }
}
