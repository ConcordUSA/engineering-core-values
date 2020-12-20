package com.example.srp.slides.following;

import com.example.srp.slides.Customer;
import com.example.srp.slides.Inventory;
import com.example.srp.slides.Order;

public class CheckoutService {

    private final Inventory inventory;

    public CheckoutService(Inventory inventory){
        this.inventory = inventory;
    }

    //boring class setup stuff omitted for space
    //this is that same class, but refactored to follow srp
    //now this class owns checking out a customer with their order
    //but it trusts the Order class to determine its cost and size,
    //the Customer class to charge the customer,
    //and if the payment is successful the Inventory class releases the inventory
    public void checkout(Customer customer, Order order){
        final int cost = order.getCost();
        if(customer.charge(cost)){
            inventory.remove(order.size());
        }
    }


    private void callCheckout(){
        //gotta have yellow method names in the screenshot
        checkout(new Customer(), new Order());
    }

}
