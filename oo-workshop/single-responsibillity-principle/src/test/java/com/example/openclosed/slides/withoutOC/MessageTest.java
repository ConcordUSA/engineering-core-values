package com.example.openclosed.slides.withoutOC;

import org.junit.Test;;

import static org.junit.Assert.assertTrue;

class MessageTest {

    @Test
public void sendEmail() {
        final Message message = new Message("OOP, yeah you know me!");
        final String email = message.sendEmail();

        // then
        assertTrue(email.contains("Email"));
    }

    @Test
public void sendSMS() {
        final Message message = new Message("OOP, yeah you know me!");
        final String smsMessage = message.sendSMS();

        // then
        assertTrue(smsMessage.contains("SMS"));
    }
}