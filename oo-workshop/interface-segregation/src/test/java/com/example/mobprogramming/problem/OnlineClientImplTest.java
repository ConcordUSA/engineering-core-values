package com.example.mobprogramming.problem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
