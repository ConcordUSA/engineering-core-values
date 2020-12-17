package slides.withoutOC;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessagePoorRefactorTest {

    @Test
    void sendEmail() {
        var given = new MessagePoorRefactor("My message","recipient@email.com","111-111-1111");
        var when = given.sendEmail();

        // then
        assertTrue(when.contains("Email"));
        assertTrue(when.contains("recipient@email.com"));
    }

    @Test
    void sendSMS() {
        var given = new MessagePoorRefactor("My message","recipient@email.com","111-111-1111");
        var when = given.sendSMS();

        // then
        assertTrue(when.contains("SMS"));
        assertTrue(when.contains("111-111-1111"));
    }
}