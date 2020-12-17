package slides.withoutOC;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void sendEmail() {
        var given = new Message("OOP, yeah you know me!");
        var when = given.sendEmail();

        // then
        assertTrue(when.contains("Email"));
    }

    @Test
    void sendSMS() {
        var given = new Message("OOP, yeah you know me!");
        var when = given.sendSMS();

        // then
        assertTrue(when.contains("SMS"));
    }
}