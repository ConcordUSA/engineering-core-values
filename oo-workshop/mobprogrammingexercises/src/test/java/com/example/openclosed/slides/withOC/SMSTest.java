package com.example.openclosed.slides.withOC;

import org.junit.Test;;

import static org.junit.Assert.assertTrue;

class SMSTest {

    @Test
public void send() {
        final SMS sms = new SMS("OOP! Yeah you know me!", "111-111-1111");
        final String smsMessage = sms.send();

        // then
        assertTrue(smsMessage.contains("SMS:"));
        assertTrue(smsMessage.contains("111-111-1111"));
    }
}