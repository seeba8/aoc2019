package utils;

public enum Direction {
    UP(0),
    RIGHT(1),
    DOWN(2),
    LEFT(3);
    private final int value;

    Direction(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }

    public Direction rotateClockwise() {
        return values()[(value + 1) % 4];
    }

    public Direction rotateCounterclockwise() {
        return values()[(value + 3) % 4];
    }
}