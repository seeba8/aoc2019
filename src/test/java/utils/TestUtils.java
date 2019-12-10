package utils;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestUtils {

    @Test
    public void testPermutations() {
        int[] i = new int[]{4, 3, 2, 1, 0};
        assertEquals(120, Utils.getPermutations(i).length);
        assertArrayEquals(new int[][]{
                new int[]{0,1,2,3,4},
                new int[]{0,1,2,4,3},
                new int[]{0,1,3,2,4}
        }, Arrays.copyOfRange(Utils.getPermutations(i),0,3));
    }

    @Test
    public void testFactorial() {
        assertEquals(1,Utils.factorial(1));
        assertEquals(2,Utils.factorial(2));
        assertEquals(6,Utils.factorial(3));
        assertEquals(24,Utils.factorial(4));
        assertEquals(120,Utils.factorial(5));
        assertEquals(720,Utils.factorial(6));
    }
}
