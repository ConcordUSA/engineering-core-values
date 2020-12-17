package com.example.mobprogramming.problem;

public class OnlineClientImpl implements RestaurantInterface {
    @Override
    public void acceptOnlineOrder() {
        //logic for placing online order
    }
    @Override
    public void takeTelephoneOrder() {
        //not applicable for online order
        throw new UnsupportedOperationException();
    }
    @Override
    public void payOnline() {
        //logic for paying online
    }
    @Override
    public void walkInCustomerOrder() {
        //not applicable for online order
        throw new UnsupportedOperationException();
    }
    @Override
    public void payInPerson() {
        //not applicable for online order
        throw new UnsupportedOperationException();
    }
}
