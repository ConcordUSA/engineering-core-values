package com.example.mobprogramming.problem;

public class TelephoneClientImpl implements RestaurantInterface {
    @Override
    public void acceptOnlineOrder() {
        //not applicable to telephone order
        throw new UnsupportedOperationException();
    }
    @Override
    public void takeTelephoneOrder() {
        //logic for placing telephone order
    }
    @Override
    public void payOnline() {
        //not applicable to telephone order
        throw new UnsupportedOperationException();
    }
    @Override
    public void walkInCustomerOrder() {
        //not applicable to telephone order
        throw new UnsupportedOperationException();
    }
    @Override
    public void payInPerson() {
        //not applicable to telephone order
        throw new UnsupportedOperationException();
    }
}
