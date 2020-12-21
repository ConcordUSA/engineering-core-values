package com.example.interfacesegregation.mobprogramming.solution;

import org.junit.Test;;

import static org.junit.Assert.assertTrue;

public class OnlinePaymentImplTest {
    @Test
    public void payOnline() {
        //arrange
        OnlinePaymentImpl given = new OnlinePaymentImpl("6.99");

        //act
        String when = given.payOnline();

        //assert
        assertTrue(when.contains("6.99"));
    }
}
