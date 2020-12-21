package com.example.openclosed.slides.withoutOC;

import org.junit.Test;;

import static org.junit.Assert.assertTrue;

class MessagePoorRefactorTest {

    @Test
public void sendEmail() {
        final MessagePoorRefactor message
                = new MessagePoorRefactor("My message","recipient@email.com","111-111-1111");
        final String emailMessage =
                message.sendEmail();

        // then
        assertTrue(emailMessage.contains("Email"));
        assertTrue(emailMessage.contains("recipient@email.com"));
    }

    @Test
public void sendSMS() {
        final MessagePoorRefactor message = new MessagePoorRefactor("My message","recipient@email.com","111-111-1111");
        final String smsMessage = message.sendSMS();

        // then
        assertTrue(smsMessage.contains("SMS"));
        assertTrue(smsMessage.contains("111-111-1111"));
    }
}