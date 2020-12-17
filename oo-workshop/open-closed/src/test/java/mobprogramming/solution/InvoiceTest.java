package mobprogramming.solution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {

    @Test
    void print() {
        var given =  new Invoice("Adam Schnaare", 1000);
        var when = given.print();

        // then
        assertTrue(when.contains("Adam Schnaare"));
        assertTrue(when.contains("1000"));
    }
}