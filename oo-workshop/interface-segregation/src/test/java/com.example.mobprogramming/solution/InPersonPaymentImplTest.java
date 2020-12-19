package com.example.mobprogramming.solution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InPersonPaymentImplTest {
    @Test
    void payInPerson(String price){
        InPersonPaymentImpl given = new InPersonPaymentImpl("9.99");
        String when = given.payInPerson();

        //then
        assertTrue(when.contains("9.99"));
    }
}
