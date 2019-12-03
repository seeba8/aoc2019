package day3;

import org.junit.Test;
import utils.Point;

import static org.junit.Assert.*;

public class TestWire {
    @Test
    public void testVerticalWireIntersect() {
        Wire longWire = new Wire(0, 0, 0, 10);
        Wire shortWire = new Wire(0, 0, 2, 7);
        assertTrue(longWire.intersects(shortWire));
        assertTrue(shortWire.intersects(longWire));

        Wire lowerWire = new Wire(0, 0, 0, 10);
        Wire upperWire = new Wire(0, 0, 2, 12);
        assertTrue(lowerWire.intersects(upperWire));
        assertTrue(upperWire.intersects(lowerWire));

        upperWire = new Wire(0, 0, 12, 22);
        assertFalse(lowerWire.intersects(upperWire));
        assertFalse(upperWire.intersects(lowerWire));

        assertTrue(upperWire.intersects(upperWire));
    }

    @Test
    public void testHorizontalWireIntersect() {
        Wire longWire = new Wire(0, 10, 0, 0);
        Wire shortWire = new Wire(2, 7, 0, 0);
        assertTrue(longWire.intersects(shortWire));
        assertTrue(shortWire.intersects(longWire));

        Wire leftWire = new Wire(0, 10, 0, 0);
        Wire rightWire = new Wire(2, 12, 0, 0);
        assertTrue(leftWire.intersects(rightWire));
        assertTrue(rightWire.intersects(leftWire));

        rightWire = new Wire(12, 22, 0, 0);
        assertFalse(leftWire.intersects(rightWire));
        assertFalse(rightWire.intersects(leftWire));

        assertTrue(rightWire.intersects(rightWire));
    }

    @Test
    public void testOrthogonalWireIntersect() {
        Wire h = new Wire(0, 10, 5, 5);
        Wire v = new Wire(5, 5, 0, 10);
        // X junction
        assertTrue(h.intersects(v));
        assertTrue(v.intersects(h));

        h = new Wire(0, 10, 0, 0);
        // T junction
        assertTrue(h.intersects(v));
        assertTrue(v.intersects(h));

        h = new Wire(0, 4, 5, 5);
        // h is left of v
        assertFalse(h.intersects(v));
        assertFalse(v.intersects(h));

        h = new Wire(6, 10, 5, 5);
        // h is right of v
        assertFalse(h.intersects(v));
        assertFalse(v.intersects(h));

        h = new Wire(0, 10, 5, 5);
        v = new Wire(0, 10, 0, 4);
        // v is below h
        assertFalse(h.intersects(v));
        assertFalse(v.intersects(h));

        v = new Wire(0, 10, 6, 10);
        // v is above h
        assertFalse(h.intersects(v));
        assertFalse(v.intersects(h));
    }

    @Test
    public void testIntersectionDistance() {
        Wire longWire = new Wire(0, 0, 0, 10);
        Wire shortWire = new Wire(0, 0, 2, 7);
        assertEquals(2, longWire.intersectDistance(shortWire));
        assertEquals(2, shortWire.intersectDistance(longWire));

        Wire lowerWire = new Wire(0, 0, 0, 10);
        Wire upperWire = new Wire(0, 0, 2, 12);
        assertEquals(2, longWire.intersectDistance(shortWire));
        assertEquals(2, shortWire.intersectDistance(longWire));

        longWire = new Wire(0, 10, 0, 0);
        shortWire = new Wire(2, 7, 0, 0);
        assertEquals(2, longWire.intersectDistance(shortWire));
        assertEquals(2, shortWire.intersectDistance(longWire));

        Wire leftWire = new Wire(0, 10, 0, 0);
        Wire rightWire = new Wire(2, 12, 0, 0);
        assertEquals(2, leftWire.intersectDistance(rightWire));
        assertEquals(2, rightWire.intersectDistance(leftWire));

        assertEquals(2, rightWire.intersectDistance(rightWire));

        Wire h = new Wire(0, 10, 5, 5);
        Wire v = new Wire(5, 5, 0, 10);
        assertEquals(10, h.intersectDistance(v));
        assertEquals(10, v.intersectDistance(h));

        h = new Wire(0, 10, 0, 0);
        assertEquals(5, h.intersectDistance(v));
        assertEquals(5, v.intersectDistance(h));
    }

    @Test(expected = IllegalStateException.class)
    public void testDoNotIntersect() {
        Wire h = new Wire(0, 10, 5, 5);
        Wire v = new Wire(0, 10, 0, 4);
        h.intersectDistance(v);
    }
    // Missing tests with negative + positive coordinates

    @Test
    public void testGetIntersectPoint() {
        Wire longWire = new Wire(0, 0, 0, 10);
        Wire shortWire = new Wire(0, 0, 2, 7);
        assertEquals(new Point<Integer>(0, 2), longWire.getIntersectPoint(shortWire));
        assertEquals(new Point<Integer>(0, 2), shortWire.getIntersectPoint(longWire));
    }
}
