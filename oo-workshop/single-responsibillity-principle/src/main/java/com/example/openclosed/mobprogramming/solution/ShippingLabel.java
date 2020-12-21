package com.example.openclosed.mobprogramming.solution;

public class ShippingLabel extends OrderReport {
    private String address;

    public ShippingLabel(String customer, int total, String address) {
        super(customer, total);
        this.address = address;
    }

    public String print(){
        String message = "Shipping Label: " + this.customer + " " + this.address;
        return message;
    }
}
