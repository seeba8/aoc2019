package day13;

public enum Tile {
    EMPTY,
    WALL,
    BLOCK,
    PADDLE,
    BALL;

    public static Tile getTile(int value) {
        switch (value) {
            case 0:
                return EMPTY;
            case 1:
                return WALL;
            case 2:
                return BLOCK;
            case 3:
                return PADDLE;
            case 4:
                return BALL;
            default:
                throw new IllegalArgumentException("Tile IDs are in range [0; 4]");
        }
    }

    public char getChar() {
        switch(this) {
            case EMPTY: return ' ';
            case WALL: return 'X';
            case BLOCK: return '#';
            case PADDLE: return '-';
            case BALL: return 'O';
            default: throw new IllegalStateException("Impossible Enum value: " + this);
        }
    }
}
