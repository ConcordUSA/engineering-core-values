package com.example.openclosed.mobprogramming.solution;

import org.junit.Test;;

import static org.junit.Assert.assertTrue;

public class ShippingLabelTest {

    @Test
    public void print() {
        final ShippingLabel shippingLabel = new ShippingLabel("Adam Schnaare", 1000, "212 This Lane");
        final String shippingLabelText = shippingLabel.print();

        // then
        assertTrue(shippingLabelText.contains("Adam Schnaare"));
        assertTrue(shippingLabelText.contains("212 This Lane"));
    }
}