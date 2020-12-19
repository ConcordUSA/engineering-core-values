package com.example.mobprogramming.solution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WalkInOrderImplTest {
    @Test
    void walkInCustomerOrder(String name, String food){
        WalkInOrderImpl given = new WalkInOrderImpl("Roger Miller", "Cheese Sandwich");
        String when = given.walkInCustomerOrder();

        //then
        assertTrue(when.contains("Roger Miller"));
        assertTrue(when.contains("Cheese Sandwich"));
    }
}
