package exercise;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReversedSequenceTest {

    private static final String SOURCE_STRING = "abcdef";
    private static final String REVERSED_STRING = "fedcba";
    private static final String EMPTY_STRING = "";

    private ReversedSequence reversedSequence;

    @BeforeEach
    void setUp() {
        reversedSequence = new ReversedSequence(SOURCE_STRING);
    }

    @Test
    void testToString() {
        assertEquals(REVERSED_STRING, reversedSequence.toString());
    }

    @Test
    void testLength() {
        assertEquals(REVERSED_STRING.length(), reversedSequence.length());
    }

    @Test
    void testCharAt() {
        assertEquals(REVERSED_STRING.charAt(2), reversedSequence.charAt(2));
    }

    @Test
    void testSubSequence() {
        assertEquals(REVERSED_STRING.subSequence(3, 5), reversedSequence.subSequence(3, 5));
    }

    @Test
    void testEmptyString() {
        var emptyString = new ReversedSequence(EMPTY_STRING);

        assertEquals(0, emptyString.length());
        assertEquals(EMPTY_STRING, emptyString.toString());
    }
}