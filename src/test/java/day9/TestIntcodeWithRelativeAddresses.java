package day9;

import day5.AdvancedIntcodeComputer;
import day5.DiagnosticComputer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.*;

public class TestIntcodeWithRelativeAddresses {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testQuine() {
        int[] quine = new int[]{109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99};
        AdvancedIntcodeComputer c = new DiagnosticComputer(quine, 0);
        c.run();
        assertArrayEquals(quine, Arrays
                .stream(outContent.toString().split("\n"))
                .mapToInt(v -> Integer.parseInt(v.trim()))
                .toArray());
    }

    @Test
    public void test16DigitNumber() {
        AdvancedIntcodeComputer c = new DiagnosticComputer(new int[]{1102, 34915192, 34915192, 7, 4, 7, 99, 0}, 0);
        c.run();
        assertEquals(16, outContent.toString().trim().length());
    }

    @Test
    public void testLargeNumber() {
        long[] programme = new long[]{104, 1125899906842624L, 99};
        AdvancedIntcodeComputer c = new DiagnosticComputer(programme, 0);
        c.run();
        assertEquals(programme[1],Long.parseLong(outContent.toString().trim()));
    }
}
