package mobprogramming.solution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShippingLabelTest {

    @Test
    void print() {
        var given =  new ShippingLabel("Adam Schnaare", 1000, "212 This Lane");
        var when = given.print();

        // then
        assertTrue(when.contains("Adam Schnaare"));
        assertTrue(when.contains("212 This Lane"));
    }
}