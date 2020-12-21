package com.example.openclosed.mobprogramming.solution;

import org.junit.Test;;

import static org.junit.Assert.assertTrue;

class InvoiceTest {

    @Test
public void print() {
        final Invoice invoice =  new Invoice("Adam Schnaare", 1000);
        final String invoiceLabel = invoice.print();

        // then
        assertTrue(invoiceLabel.contains("Adam Schnaare"));
        assertTrue(invoiceLabel.contains("1000"));
    }
}