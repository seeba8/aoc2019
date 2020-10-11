package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDirection {
    @Test
    public void testClockwiseRotation() {
        Direction direction = Direction.UP;
        direction = direction.rotateClockwise();
        assertEquals(Direction.RIGHT, direction);

        direction = direction.rotateClockwise();
        assertEquals(Direction.DOWN, direction);

        direction = direction.rotateClockwise();
        assertEquals(Direction.LEFT, direction);

        direction = direction.rotateClockwise();
        assertEquals(Direction.UP, direction);
    }

    @Test
    public void testCounterclockwiseRotation() {
        Direction direction = Direction.UP;
        direction = direction.rotateCounterclockwise();
        assertEquals(Direction.LEFT, direction);

        direction = direction.rotateCounterclockwise();
        assertEquals(Direction.DOWN, direction);

        direction = direction.rotateCounterclockwise();
        assertEquals(Direction.RIGHT, direction);

        direction = direction.rotateCounterclockwise();
        assertEquals(Direction.UP, direction);
    }
}
