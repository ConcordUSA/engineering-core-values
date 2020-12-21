package com.example.interfacesegregation.mobprogramming.solution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InPersonPaymentImplTest {
    @Test
    void payInPerson(){
        //arrange
        InPersonPaymentImpl given = new InPersonPaymentImpl("9.99");

        //act
        String when = given.payInPerson();

        //assert
        assertTrue(when.contains("9.99"));
    }
}
