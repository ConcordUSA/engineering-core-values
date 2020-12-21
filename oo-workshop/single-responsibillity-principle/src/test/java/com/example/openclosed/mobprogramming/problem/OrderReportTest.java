package com.example.openclosed.mobprogramming.problem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderReportTest {

    @Test
    void invoice() {
        var given = new OrderReport("Adam Schnaare",1000);
        var when = given.invoice();

        // then
        assertTrue(when.contains("Invoice"));
        assertTrue(when.contains("Adam Schnaare"));
        assertTrue(when.contains("1000"));
    }

    @Test
    void shippingLabel() {
        var given = new OrderReport("Adam Schnaare",1000);
        var when = given.shippingLabel();

        // then
        assertTrue(when.contains("Shipping Label"));
        assertTrue(when.contains("Adam Schnaare"));
    }
}