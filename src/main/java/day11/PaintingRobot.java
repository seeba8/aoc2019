package day11;

import day5.AdvancedIntcodeComputer;
import utils.Direction;
import utils.Point;

public class PaintingRobot extends AdvancedIntcodeComputer {
    private Point<Integer> position;
    private State state;
    private Direction direction;
    private Hull hull;

    public PaintingRobot(long[] programme) {
        super(programme, 0);
        hull = new Hull();
        direction = Direction.UP;
        position = new Point<>(0, 0);
        state = State.PAINT_MODE;
    }

    public Hull getHull() {
        return hull;
    }

    public Point<Integer> getPosition() {
        return position;
    }

    public int getNumberOfPaintedPanels() {
        return hull.getNumberOfPaintedPanels();
    }

    @Override
    protected void output(long value) {
        switch (state) {
            case ROTATE_MODE:
                direction = value == 0 ? direction.rotateCounterclockwise() : direction.rotateClockwise();
                move();
                state = State.PAINT_MODE;
                break;
            case PAINT_MODE:
                hull.paint(this.position, value == 0 ? Hull.Colour.BLACK : Hull.Colour.WHITE);
                state = State.ROTATE_MODE;
                break;
        }
    }

    @Override
    protected long getInput() {
        return hull.getColour(position).ordinal();
    }

    private void move() {
        switch (direction) {
            case UP:
                position.y -= 1;
                break;
            case RIGHT:
                position.x += 1;
                break;
            case DOWN:
                position.y += 1;
                break;
            case LEFT:
                position.x -= 1;
                break;
        }
    }

    private enum State {
        ROTATE_MODE,
        PAINT_MODE
    }

}
