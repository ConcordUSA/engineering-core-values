package com.example.openclosed.slides.withOC;

import org.junit.Test;

import static org.junit.Assert.assertTrue;;

class EmailTest {

    @Test
public void send() {
        final Email email = new Email("OOP! Yeah you know me!", "receiver@email.com");
        final String emailMessage = email.send();

        // then
        assertTrue(emailMessage.contains("Email:"));
        assertTrue(emailMessage.contains("receiver@email.com"));
    }
}