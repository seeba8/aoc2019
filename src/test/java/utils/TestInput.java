package utils;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


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

    @Test
    public void testSingleLineArray() {
        try {
            assertArrayEquals(new int[]{1, 0, 0, 3, 1},
                    Arrays.copyOf(Input.getIntArrayFromSingleLine("day2.txt",","),5));
        }
        catch(Exception e) {
            fail("could not read file");
        }
    }

    @Test
    public void testLinesSplit() {
        try {
            String[][] result = Input.getLinesSplit("day3.txt",",");
            assertEquals(2,result.length);
            assertArrayEquals("R1004,U518,R309,D991,R436".split(","),
                    Arrays.copyOf(result[0],5));
            assertArrayEquals("L998,U952,R204,U266,R353".split(","),
                    Arrays.copyOf(result[1],5));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testEmptySeparator() {
        int[] result = new int[]{};
        try {
            result = Input.getIntArrayFromSingleLine("day8.txt","");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            fail();
        }
        assertArrayEquals(new int[]{1,2,2,2,2}, Arrays.copyOf(result,5));
    }
}
