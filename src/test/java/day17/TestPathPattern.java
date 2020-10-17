package day17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPathPattern {
    @Test
    void testApplyPattern() {
        PathPattern p = new PathPattern();
        p.mainRoutine = "A,B,C,B,A,C";
        p.patterns = Arrays.asList("R,8,R,8", "R,4,R,4,R,8", "L,6,L,2");
        assertEquals("R,8,R,8,R,4,R,4,R,8,L,6,L,2,R,4,R,4,R,8,R,8,R,8,L,6,L,2", p.applyPattern());
    }

    @Test
    void testFindPattern() {
        PathPattern p = new PathPattern();
        assertEquals("R,8,R,8", p.findPattern("R,8,R,8,R,4,R,4,R,8,L,6,L,2,R,4,R,4,R,8,R,8,R,8,L,6,L,2"));
    }

    @Test
    void testGetSteps() {
        assertArrayEquals(new String[]{"R,8", "R,8", "R,4", "R,4", "R,8", "L,6", "L,2", "R,4", "R,4", "R,8", "R,8", "R,8", "L,6", "L,2"},
                PathPattern.getSteps("R,8,R,8,R,4,R,4,R,8,L,6,L,2,R,4,R,4,R,8,R,8,R,8,L,6,L,2"));
    }

    @Test
    void testFromPath() {
        PathPattern p = new PathPattern();
        p.path = "R,8,R,8,R,4,R,4,R,8,L,6,L,2,R,4,R,4,R,8,R,8,R,8,L,6,L,2";
        p.splitIntoPatterns();
        Assertions.assertTrue(p.patterns.size() <= 3);
        assertEquals("A,B,C,B,A,C", p.mainRoutine);
    }


}
