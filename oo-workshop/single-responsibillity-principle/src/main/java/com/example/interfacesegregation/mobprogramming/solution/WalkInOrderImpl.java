package com.example.interfacesegregation.mobprogramming.solution;

public class WalkInOrderImpl implements OrderInterface {
    public String name;
    public String food;

    public WalkInOrderImpl(String name, String food){
        this.name = name;
        this.food = food;
    }

    @Override
    public void placeOrder() {
        walkInCustomerOrder();
    }

    public String walkInCustomerOrder(){
        String message = "Walk-in order from " + this.name + " for " + this.food;
        return message;
    }
}
