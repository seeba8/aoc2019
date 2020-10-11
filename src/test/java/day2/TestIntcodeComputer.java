package day2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestIntcodeComputer {
    @Test
    public void testReturnValue() {
        assertEquals(3500, new IntcodeComputer(new int[]{1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50}).run());
        assertEquals(2, new IntcodeComputer(new int[]{2, 3, 0, 3, 99}).run());
        assertEquals(2, new IntcodeComputer(new int[]{2, 3, 0, 3, 99}).run());
        assertEquals(2, new IntcodeComputer(new int[]{2, 4, 4, 5, 99, 0}).run());
        assertEquals(30, new IntcodeComputer(new int[]{1, 1, 1, 4, 99, 5, 6, 0, 99}).run());
    }

    @Test
    public void testProgrammeEndState() {
        IntcodeComputer ic = new IntcodeComputer(new int[]{1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50});
        ic.run();
        assertArrayEquals(new int[]{3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50}, ic.programme);

        ic = new IntcodeComputer(new int[]{1, 0, 0, 0, 99});
        ic.run();
        assertArrayEquals(new int[]{2, 0, 0, 0, 99}, ic.programme);

        ic = new IntcodeComputer(new int[]{2, 3, 0, 3, 99});
        ic.run();
        assertArrayEquals(new int[]{2, 3, 0, 6, 99}, ic.programme);

        ic = new IntcodeComputer(new int[]{2, 4, 4, 5, 99, 0});
        ic.run();
        assertArrayEquals(new int[]{2, 4, 4, 5, 99, 9801}, ic.programme);

        ic = new IntcodeComputer(new int[]{1, 1, 1, 4, 99, 5, 6, 0, 99});
        ic.run();
        assertArrayEquals(new int[]{30, 1, 1, 4, 2, 5, 6, 0, 99}, ic.programme);

    }
}
