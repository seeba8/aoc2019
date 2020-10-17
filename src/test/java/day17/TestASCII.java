package day17;

import org.junit.jupiter.api.Test;
import utils.Point;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestASCII {

    @Test
    void testGetIntersections() {
        ASCII ascii = new ASCII(new long[]{}, false);
        ascii.setScaffoldView(new String[]{
                "..#..........",
                "..#..........",
                "#######...###",
                "#.#...#...#.#",
                "#############",
                "..#...#...#..",
                "..#####...^.."
        });
        assertEquals(76, ascii.getAlignmentParameters());
    }

    @Test
    void testGetRobotLocation() {
        ASCII ascii = new ASCII(new long[]{}, false);
        ascii.scaffoldView = Arrays.asList(
                "#######...#####",
                "#.....#...#...#",
                "#.....#...#...#",
                "......#...#...#",
                "......#...###.#",
                "......#.....#.#",
                "^########...#.#",
                "......#.#...#.#",
                "......#########",
                "........#...#..",
                "....#########..",
                "....#...#......",
                "....#...#......",
                "....#...#......",
                "....#####......"
        );

        assertEquals(new Point<Integer>(0, 6), ascii.getRobotLocation());
    }

    @Test
    void testGetPath() {
        ASCII ascii = new ASCII(new long[]{}, false);
        ascii.scaffoldView = Arrays.asList(
                "#######...#####",
                "#.....#...#...#",
                "#.....#...#...#",
                "......#...#...#",
                "......#...###.#",
                "......#.....#.#",
                "^########...#.#",
                "......#.#...#.#",
                "......#########",
                "........#...#..",
                "....#########..",
                "....#...#......",
                "....#...#......",
                "....#...#......",
                "....#####......"
        );
        //System.out.println(ascii.getScaffoldString());
        assertEquals("R,8,R,8,R,4,R,4,R,8,L,6,L,2,R,4,R,4,R,8,R,8,R,8,L,6,L,2", ascii.getPath());
    }
}
