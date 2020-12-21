package com.example.openclosed.mobprogramming.solution;

public class Invoice extends OrderReport {
    public Invoice(String customer, int total) {
        super(customer, total);
    }

    public String print() {
        var message = "Invoice: " + this.customer + " " + this.total;
        return message;
    }
}
