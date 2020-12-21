package com.example.openclosed.mobprogramming.problem;

import org.junit.Test;;

import static org.junit.Assert.assertTrue;

class OrderReportTest {

    @Test
public void invoice() {
        final OrderReport given = new OrderReport("Adam Schnaare",1000);
        final String when = given.invoice();

        // then
        assertTrue(when.contains("Invoice"));
        assertTrue(when.contains("Adam Schnaare"));
        assertTrue(when.contains("1000"));
    }

    @Test
public void shippingLabel() {
        final OrderReport given = new OrderReport("Adam Schnaare",1000);
        final String when = given.shippingLabel();

        // then
        assertTrue(when.contains("Shipping Label"));
        assertTrue(when.contains("Adam Schnaare"));
    }
}