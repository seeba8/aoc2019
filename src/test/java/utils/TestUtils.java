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
                new int[]{0, 1, 2, 3, 4},
                new int[]{0, 1, 2, 4, 3},
                new int[]{0, 1, 3, 2, 4}
        }, Arrays.copyOfRange(Utils.getPermutations(i), 0, 3));
    }

    @Test
    public void testFactorial() {
        assertEquals(1, Utils.factorial(1));
        assertEquals(2, Utils.factorial(2));
        assertEquals(6, Utils.factorial(3));
        assertEquals(24, Utils.factorial(4));
        assertEquals(120, Utils.factorial(5));
        assertEquals(720, Utils.factorial(6));
    }

    @Test
    public void testGCD() {
        assertEquals(6, Utils.gcd(48, 18));
        assertEquals(6, Utils.gcd(18, 48));
        assertEquals(12, Utils.gcd(48, 180));
    }

    @Test
    public void testLCM() {
        assertEquals(252, Utils.lcm(18, 28));
        assertEquals(2772, Utils.lcm(new long[]{18, 28, 44}));
    }

    @Test
    public void testdotProduct() {
        assertEquals(14 + 18 + 20, Utils.dotProduct(new int[]{2, 3, 4}, new int[]{7, 6, 5}));
        assertEquals(14 + 18 + 20 + 0, Utils.dotProduct(new int[]{2, 3, 4}, new int[]{7, 6, 5, 10}));
        assertEquals(0, Utils.dotProduct(new int[]{}, new int[]{1, 2, 3}));
    }

    @Test
    public void testdotProductModulo() {
        assertEquals(2 * 7 + 3 * 6 + 4 * 5, Utils.dotProductModulo(new int[]{2, 3, 4}, new int[]{7, 6, 5}));
        assertEquals(2 * 7 + 3 * 6 + 4 * 5 + 2 * 10, Utils.dotProductModulo(new int[]{2, 3, 4}, new int[]{7, 6, 5, 10}));
    }
}
