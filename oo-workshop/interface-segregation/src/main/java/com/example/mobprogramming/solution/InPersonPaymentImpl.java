package com.example.mobprogramming.solution;

public class InPersonPaymentImpl implements PaymentInterface {
    public String price;

    public InPersonPaymentImpl(String price){
        this.price = price;
    }

    @Override
    public void payForOrder() {
        payInPerson();
    }

    public String payInPerson(){
        String message = "In-person payment for $" + this.price + " processed";
        return message;
    }
}
