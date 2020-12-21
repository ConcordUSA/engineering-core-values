package com.example.interfacesegregation.mobprogramming.problem;

import org.junit.Test;;

import static org.junit.Assert.assertTrue;

public class OnlineClientImplTest {
    @Test
    void acceptOnlineOrder(){
        //arrange
        OnlineClientImpl given = new OnlineClientImpl("Brandon Gilbert", "Salad", "6.99");

        //act
        String when = given.acceptOnlineOrder();

        //assert
        assertTrue(when.contains("Brandon Gilbert"));
        assertTrue(when.contains("Salad"));
    }

    @Test
    void payOnline(){
        //arrange
        OnlineClientImpl given = new OnlineClientImpl("Brandon Gilbert", "Salad","6.99");

        //act
        String when = given.payOnline();

        //assert
        assertTrue(when.contains("6.99"));
    }
}
