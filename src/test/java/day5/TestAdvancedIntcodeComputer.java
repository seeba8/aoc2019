package day5;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class TestAdvancedIntcodeComputer {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testExample1() {
        AdvancedIntcodeComputer c = new AdvancedIntcodeComputer(new int[]{3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8}, 8);
        c.run();
        assertEquals("1", outContent.toString().trim());

        outContent.reset();
        c.setInput(7);
        c.run();
        assertEquals("0", outContent.toString().trim());
    }

    @Test
    public void testExample2() {
        AdvancedIntcodeComputer c = new AdvancedIntcodeComputer(new int[]{3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8}, 7);
        c.run();
        assertEquals("1", outContent.toString().trim());

        outContent.reset();
        c.setInput(8);
        c.run();
        assertEquals("0", outContent.toString().trim());

        outContent.reset();
        c.setInput(9);
        c.run();
        assertEquals("0", outContent.toString().trim());

        outContent.reset();
        c.setInput(-7);
        c.run();
        assertEquals("1", outContent.toString().trim());

        outContent.reset();
        c.setInput(-9);
        c.run();
        assertEquals("1", outContent.toString().trim());
    }

    @Test
    public void testExample3() {
        AdvancedIntcodeComputer c = new AdvancedIntcodeComputer(new int[]{3, 3, 1108, -1, 8, 3, 4, 3, 99}, 8);
        c.run();
        assertEquals("1", outContent.toString().trim());

        outContent.reset();
        c.setInput(7);
        c.run();
        assertEquals("0", outContent.toString().trim());
    }

    @Test
    public void testExample4() {
        AdvancedIntcodeComputer c = new AdvancedIntcodeComputer(new int[]{3, 3, 1107, -1, 8, 3, 4, 3, 99}, 7);
        c.run();
        assertEquals("1", outContent.toString().trim());

        outContent.reset();
        c.setInput(8);
        c.run();
        assertEquals("0", outContent.toString().trim());

        outContent.reset();
        c.setInput(9);
        c.run();
        assertEquals("0", outContent.toString().trim());

        outContent.reset();
        c.setInput(-7);
        c.run();
        assertEquals("1", outContent.toString().trim());

        outContent.reset();
        c.setInput(-9);
        c.run();
        assertEquals("1", outContent.toString().trim());
    }

    @Test
    public void testExample5() {
        AdvancedIntcodeComputer c = new AdvancedIntcodeComputer(new int[]{3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9}, 8);
        c.run();
        assertEquals("1", outContent.toString().trim());

        outContent.reset();
        c = new AdvancedIntcodeComputer(new int[]{3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9}, 0);
        c.run();
        assertEquals("0", outContent.toString().trim());
    }

    @Test
    public void testExample6() {
        AdvancedIntcodeComputer c = new AdvancedIntcodeComputer(new int[]{3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1}, 8);
        c.run();
        assertEquals("1", outContent.toString().trim());

        outContent.reset();
        c = new AdvancedIntcodeComputer(new int[]{3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1}, 0);
        c.run();
        assertEquals("0", outContent.toString().trim());
    }

    @Test
    public void testExample7() {
        AdvancedIntcodeComputer c = new AdvancedIntcodeComputer(new int[]{3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
        }, 7);
        c.run();
        assertEquals("999", outContent.toString().trim());

        outContent.reset();
        c = new AdvancedIntcodeComputer(new int[]{3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99}, 8);
        c.run();
        assertEquals("1000", outContent.toString().trim());

        outContent.reset();
        c = new AdvancedIntcodeComputer(new int[]{3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99}, 9);
        c.run();
        assertEquals("1001", outContent.toString().trim());
    }

}
