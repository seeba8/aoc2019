package day11;

import org.junit.jupiter.api.Test;
import utils.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPaintingRobot {

    @Test
    public void testFirstStep() {
        long[] programme = new long[]{1, 0, 1, 100, 4, 0, 4, 1, 99};
        PaintingRobot robot = new PaintingRobot(programme);
        robot.run();
        assertEquals(new Point<Integer>(-1, 0), robot.getPosition());
        assertEquals(1, robot.getNumberOfPaintedPanels());
    }

    @Test
    public void testSecondStep() {
        long[] programme = new long[]{1, 0, 1, 100, 4, 0, 4, 1, 4, 1, 4, 1, 99};
        PaintingRobot robot = new PaintingRobot(programme);
        robot.run();
        assertEquals(new Point<Integer>(-1, 1), robot.getPosition());
        assertEquals(2, robot.getNumberOfPaintedPanels());
    }

    @Test
    public void testThirdFourthStep() {
        long[] programme = new long[]{1, 0, 1, 100, 4, 0, 4, 1, 4, 1, 4, 1, 4, 0, 4, 1, 4, 0, 4, 1, 99};
        PaintingRobot robot = new PaintingRobot(programme);
        robot.run();
        assertEquals(new Point<Integer>(0, 0), robot.getPosition());
        assertEquals(4, robot.getNumberOfPaintedPanels());
    }

    @Test
    public void testLastSteps() {
        long[] programme = new long[]{1, 0, 1, 100, 4, 0, 4, 1, 4, 1, 4, 1, 4, 0, 4, 1, 4, 0, 4, 1, 4, 1, 4, 0, 4, 0, 4, 1, 4, 0, 4, 1, 99};
        PaintingRobot robot = new PaintingRobot(programme);
        robot.run();
        assertEquals(new Point<Integer>(0, -1), robot.getPosition());
        assertEquals(6, robot.getNumberOfPaintedPanels());
    }

}
