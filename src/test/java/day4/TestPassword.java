package day4;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestPassword {
    @Test
    public void testTwins() {
        Password p = new Password(100000, 900000);
        assertTrue(p.hasTwin(112345));
        assertTrue(p.hasTwin(123455));
        assertTrue(p.hasTwin(123345));
        assertFalse(p.hasTwin(123456));
    }

    @Test
    public void testIncreasing() {
        Password p = new Password(100000, 900000);
        assertTrue(p.isIncreasing(112345));
        assertTrue(p.isIncreasing(123456));
        assertTrue(p.isIncreasing(111111));
        assertFalse(p.isIncreasing(111110));
        assertFalse(p.isIncreasing(123454));
    }

    @Test
    public void testValid() {
        Password p = new Password(123257, 647015);
        assertFalse(p.isValid(223450));
        assertFalse(p.isValid(123789));
    }

    @Test
    public void testExactTwin() {
        Password p = new Password(100000, 900000);
        assertTrue(p.hasExactTwin(112233));
        assertTrue(p.hasExactTwin(111122));
        assertFalse(p.hasExactTwin(123444));
    }
}
