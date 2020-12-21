package com.example.srp.slides;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public List<Item> getItems() {
        //maybe someday we'll be able to add items to an order
        //today is not that day
        return new ArrayList<>();
    }

    public int getCost() {
        //five finger discount
        return 0;
    }

    public int size() {
        //this makes the cost make sense
        return 0;
    }
}
