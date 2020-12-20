package com.example.mobprogramming.problem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WalkInClientImplTest {
    @Test
    void walkInCustomerOrder(String name, String food){
        //arrange
        WalkInClientImpl given = new WalkInClientImpl("Roger Miller", "Cheese Sandwich", "9.99");

        //act
        String when = given.walkInCustomerOrder();

        //assert
        assertTrue(when.contains("Roger Miller"));
        assertTrue(when.contains("Cheese Sandwich"));
    }

    @Test
    void payInPerson(String price){
        //arrange
        WalkInClientImpl given = new WalkInClientImpl("Roger Miller", "Cheese Sandwich", "9.99");

        //act
        String when = given.payInPerson();

        //assert
        assertTrue(when.contains("9.99"));
    }
}
