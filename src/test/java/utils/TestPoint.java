package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void testPointClone() {
        Point<Integer> a = new Point<>(0, 0);
        Point<Integer> clone = a.clone();
        a.x = 1;
        assertEquals(new Point<Integer>(0,0), clone);
        assertEquals(0, clone.x.intValue());

    }
}
