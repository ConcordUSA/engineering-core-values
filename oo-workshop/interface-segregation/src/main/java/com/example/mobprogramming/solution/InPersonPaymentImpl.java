package com.example.mobprogramming.solution;

public class InPersonPaymentImpl implements PaymentInterface {
    @Override
    public void payForOrder() {
        payInPerson();
    }

    public void payInPerson(){
        //logic for handling in-person payments
    }
}
