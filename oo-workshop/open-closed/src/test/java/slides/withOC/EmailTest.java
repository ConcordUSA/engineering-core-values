package slides.withOC;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void send() {
        var given = new Email("OOP! Yeah you know me!", "receiver@email.com");
        var when = given.send();

        // then
        assertTrue(when.contains("Email:"));
        assertTrue(when.contains("receiver@email.com"));
    }
}