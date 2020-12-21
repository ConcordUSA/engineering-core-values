package com.example.interfacesegregation.mobprogramming.problem;

import org.junit.Test;;

import static org.junit.Assert.assertTrue;

public class WalkInClientImplTest {
    @Test
    void walkInCustomerOrder(){
        //arrange
        WalkInClientImpl given = new WalkInClientImpl("Roger Miller", "Cheese Sandwich", "9.99");

        //act
        String when = given.walkInCustomerOrder();

        //assert
        assertTrue(when.contains("Roger Miller"));
        assertTrue(when.contains("Cheese Sandwich"));
    }

    @Test
    void payInPerson(){
        //arrange
        WalkInClientImpl given = new WalkInClientImpl("Roger Miller", "Cheese Sandwich", "9.99");

        //act
        String when = given.payInPerson();

        //assert
        assertTrue(when.contains("9.99"));
    }
}
