package com.example.mobprogramming.problem;

public class WalkInClientImpl implements RestaurantInterface{
    @Override
    public void acceptOnlineOrder() {
        //not applicable for walk-in client
        throw new UnsupportedOperationException();
    }
    @Override
    public void takeTelephoneOrder() {
        //not applicable for walk-in client
        throw new UnsupportedOperationException();
    }
    @Override
    public void payOnline() {
        //not applicable for walk-in client
        throw new UnsupportedOperationException();
    }
    @Override
    public void walkInCustomerOrder() {
        //logic for placing walk-in customer order
    }
    @Override
    public void payInPerson() {
        //logic for paying in person
    }
}
