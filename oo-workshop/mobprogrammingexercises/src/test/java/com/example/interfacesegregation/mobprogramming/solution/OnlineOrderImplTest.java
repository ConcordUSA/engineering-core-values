package com.example.interfacesegregation.mobprogramming.solution;

import org.junit.Test;;

import static org.junit.Assert.assertTrue;

public class OnlineOrderImplTest {
    @Test
    public void acceptOnlineOrder() {
        //arrange
        OnlineOrderImpl given = new OnlineOrderImpl("Brandon Gilbert", "Salad");

        //act
        String when = given.acceptOnlineOrder();

        //assert
        assertTrue(when.contains("Brandon Gilbert"));
        assertTrue(when.contains("Salad"));
    }
}
