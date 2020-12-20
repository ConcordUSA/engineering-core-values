package com.example.mobprogramming.solution;

public class OnlinePaymentImpl implements PaymentInterface {
    public String price;

    public OnlinePaymentImpl(String price){
        this.price = price;
    }
    @Override
    public void payForOrder() {
        payOnline();
    }

    public String payOnline(){
        String message = "Online payment for $" + this.price + " processed";
        return message;
    }
}
