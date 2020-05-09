package day15;

public enum Command {
    NORTH(1),
    SOUTH(2),
    WEST(3),
    EAST(4);
    private final int direction;

    Command(int direction) {
        this.direction = direction;
    }

    int intValue() {
        return direction;
    }

    Command reverse() {
        switch (this) {
            case NORTH:
                return Command.SOUTH;
            case SOUTH:
                return Command.NORTH;
            case WEST:
                return Command.EAST;
            case EAST:
                return Command.WEST;
            default:
                throw new IllegalStateException("There's no other value");
        }
    }
}
