package day3;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;


public class TestWires {
    @Test
    public void testAllIntersectionDistances() {
        Wires w = new Wires("R8,U5,L5,D3".split(","),
                "U7,R6,D4,L4".split(","));
        int[] result = w.getAllIntersectDistances();
        Arrays.sort(result);
        assertArrayEquals(new int[]{6, 11}, result);
    }

    @Test
    public void testClosestIntersectionDistance() {
        Wires w = new Wires("R75,D30,R83,U83,L12,D49,R71,U7,L72".split(","),
                "U62,R66,U55,R34,D71,R55,D58,R83".split(","));
        assertEquals(159, w.getMinimumIntersectDistance());

        w = new Wires("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51".split(","),
                "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7".split(","));
        assertEquals(135, w.getMinimumIntersectDistance());

    }

    @Test
    public void testMinimumWireLength() {
        Wires w = new Wires("R8,U5,L5,D3".split(","),
                "U7,R6,D4,L4".split(","));
        assertEquals(30, w.getShortestWireLengthIntersection());

        w = new Wires("R75,D30,R83,U83,L12,D49,R71,U7,L72".split(","),
                "U62,R66,U55,R34,D71,R55,D58,R83".split(","));
        assertEquals(610, w.getShortestWireLengthIntersection());

        w = new Wires("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51".split(","),
                "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7".split(","));
        assertEquals(410, w.getShortestWireLengthIntersection());

    }

}
