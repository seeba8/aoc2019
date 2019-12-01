package utils;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestInput {

    @Test
    public void testIntArrayInput() {
        try {
            assertArrayEquals(new int[]{80590, 86055, 92321, 131464, 73326},
                    Arrays.copyOf(Input.getIntegerArray("day1.txt"),5));
        }
        catch(Exception e) {
            fail("could not read file");
        }

    }
}
