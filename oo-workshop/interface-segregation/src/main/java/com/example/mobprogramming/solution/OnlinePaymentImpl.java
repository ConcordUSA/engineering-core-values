package com.example.mobprogramming.solution;

public class OnlinePaymentImpl implements PaymentInterface {
    @Override
    public void payForOrder() {
        payOnline();
    }

    public void payOnline(){
        //logic for handling online payments
    }
}
