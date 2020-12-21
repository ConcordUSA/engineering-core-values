package com.example.srp.slides.violating.discounted;

import com.example.srp.slides.Inventory;
import com.example.srp.slides.Item;
import com.example.srp.slides.Order;

import java.util.List;

public class CheckoutService {

    private final Inventory inventory;
    private final List<Item> items;

    public CheckoutService(Order order, Inventory inventory){
        this.items = order.getItems();
        this.inventory = inventory;
    }

    //boring class setup stuff omitted for space
    //this is that same class, but costs have a discount applied
    public void checkout(){
        final int cost = getOrderCost();
        if(chargeCustomer(cost)){
            removeInventory();
        }
    }

    private int getOrderCost(){
        //The spec said BOGO...
        //If you buy one you will get that one for free
        return items.size()/2;
    }

    private void removeInventory(){
        //Doubt there's any reason to change this
        inventory.remove(getOrderCost());
    }

    private boolean chargeCustomer(int cost) {
        //let's just say you don't pay...
        //with money
        //https://y.yarn.co/4f77d2a4-c710-4c3a-ade8-f4f48c1ec86d_text_hi.gif
        return true;
    }

    private void callCheckout(){
        //gotta have yellow method names in the screenshot
        checkout();
    }
}
