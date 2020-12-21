package com.example.openclosed.slides.withOC;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SMSTest {

    @Test
    void send() {
        var given = new SMS("OOP! Yeah you know me!", "111-111-1111");
        var when = given.send();

        // then
        assertTrue(when.contains("SMS:"));
        assertTrue(when.contains("111-111-1111"));
    }
}