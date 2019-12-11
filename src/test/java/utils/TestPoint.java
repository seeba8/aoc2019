package utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestPoint {

    @Test
    public void testPointComparison() {
        Point<Integer> a = new Point<>(0, 0);
        Point<Integer> b = new Point<>(0, 0);
        assertEquals(a, b);
        assertTrue(a.equals(b));
        //assertSame(a,b);
        //assertTrue(a==b);
    }
}
