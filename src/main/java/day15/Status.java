package day15;

public enum Status {
    WALL(0),
    MOVED(1),
    MOVED_OXYGEN(2);
    private int status;

    Status(int status) {
        this.status = status;
    }

    int intValue() {
        return status;
    }

    static Status fromValue(int value) {
        switch (value) {
            case 0:
                return Status.WALL;
            case 1:
                return Status.MOVED;
            case 2:
                return Status.MOVED_OXYGEN;
            default:
                throw new IllegalStateException("Illegal Status: " + value);
        }
    }
}
