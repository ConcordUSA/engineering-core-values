package com.example.mobprogramming.solution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OnlineOrderImplTest {
    @Test
    void acceptOnlineOrder(String name, String food){
        OnlineOrderImpl given = new OnlineOrderImpl("Brandon Gilbert", "Salad");
        String when = given.acceptOnlineOrder();

        //then
        assertTrue(when.contains("Brandon Gilbert"));
        assertTrue(when.contains("Salad"));
    }
}
