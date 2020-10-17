package day17;

import day5.AdvancedIntcodeComputer;
import utils.Point;
import utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class ASCII extends AdvancedIntcodeComputer {
    List<String> scaffoldView = new ArrayList<>();
    Queue<Long> inputs;
    private boolean rescue;

    public ASCII(long[] programme, boolean rescue) {
        super(programme, 0L);
        scaffoldView.add("");
        this.rescue = rescue;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        long[] programme = utils.Input.getLongArrayFromSingleLine("day17.txt", ",");
        ASCII ascii = new ASCII(programme.clone(), false);
        ascii.run();
        System.out.printf("Alignment parameters: %s\n", ascii.getAlignmentParameters());
        System.out.println(ascii.getScaffoldString());
        String[] view = ascii.getScaffoldView();
        String path = ascii.getPath();
        System.out.println(path);
        PathPattern p = new PathPattern();
        p.path = path;
        p.splitIntoPatterns();
        assert (p.applyPattern().equals(path));
        long[] p2 = programme.clone();
        p2[0] = 2;

        ASCII rescue = new ASCII(p2, true);
        rescue.inputs = getInputs(p.mainRoutine, p.patterns.toArray(new String[0]), false);
        rescue.run();


    }

    static Queue<Long> getInputs(String mainRoutine, String[] patterns, boolean videoFeed) {
        Queue<Long> input = new LinkedList<>(Utils.charArrayToLongArray((mainRoutine + "\n").toCharArray()));
        for (String p : patterns) {
            input.addAll(Utils.charArrayToLongArray((p + "\n").toCharArray()));
        }
        if (videoFeed) {
            input.addAll(Arrays.asList((long) 'y', (long) '\n'));
        } else {
            input.addAll(Arrays.asList((long) 'n', (long) '\n'));
        }
        return input;
    }

    String getScaffoldString() {
        return scaffoldView.stream().collect(Collectors.joining("\n"));
    }

    @Override
    public long run() {
        long res = super.run();
        scaffoldView.remove(scaffoldView.size() - 1);
        return res;
    }

    int getAlignmentParameters() {
        int sum = 0;
        for (int y = 0; y < scaffoldView.size(); y++) {
            for (int x = 0; x < scaffoldView.get(0).length(); x++) {
                if (isIntersection(x, y)) {
                    sum += x * y;
                }
            }
        }
        return sum;
    }

    boolean isIntersection(int x, int y) {
        if (x < 1 || y < 1 || x > scaffoldView.get(0).length() - 2 || y > scaffoldView.size() - 2) return false;
        if (scaffoldView.get(y).charAt(x) != '#') return false;
        if (scaffoldView.get(y).charAt(x - 1) != '#') return false;
        if (scaffoldView.get(y).charAt(x + 1) != '#') return false;
        if (scaffoldView.get(y - 1).charAt(x) != '#') return false;
        return scaffoldView.get(y + 1).charAt(x) == '#';
    }

    public String[] getScaffoldView() {
        return this.scaffoldView.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList()).toArray(new String[0]);
    }

    public void setScaffoldView(String[] scaffoldView) {
        this.scaffoldView = Arrays.asList(scaffoldView);
    }

    @Override
    protected long getInput() {
        if (!rescue) return 0;
        if (inputs.isEmpty()) return 0;
        return inputs.poll();
    }

    @Override
    protected void output(long value) {
        if (rescue) System.out.println("DUST: " + value);
        char output = (char) value;
        int currentIndex = scaffoldView.size() - 1;
        switch (output) {
            case '#':
            case '.':
            case '<':
            case '>':
            case '^':
            case 'v':
                scaffoldView.set(currentIndex, scaffoldView.get(currentIndex) + output);
                break;
            case '\n':
                scaffoldView.add("");
        }
    }

    protected Point<Integer> getRobotLocation() {
        for (int rowNum = 0; rowNum < scaffoldView.size(); rowNum++) {
            for (String robot : "^<>v".split("")) {
                int idx = scaffoldView.get(rowNum).indexOf(robot);
                if (idx != -1) {
                    return new Point<Integer>(idx, rowNum);
                }
            }
        }
        throw new IllegalStateException("No robot found");
    }


    protected DIRECTION getRobotDirection() {
        Point<Integer> robot = getRobotLocation();
        int straightCount = 0;
        DIRECTION direction;
        switch (scaffoldView.get(robot.y).charAt(robot.x)) {
            case '^':
                return DIRECTION.UP;
            case '<':
                return DIRECTION.LEFT;
            case '>':
                return DIRECTION.RIGHT;
            case 'v':
                return DIRECTION.DOWN;
            default:
                throw new IllegalStateException("Illegal direction: " + scaffoldView.get(robot.y).charAt(robot.x));
        }
    }

    protected boolean canGo(Point<Integer> robot, DIRECTION direction) {
        String[] field = getScaffoldView();
        switch (direction) {
            case UP:
                return robot.y != 0 && field[robot.y - 1].charAt(robot.x) == '#';
            case RIGHT:
                return robot.x != field[0].length() - 1 && field[robot.y].charAt(robot.x + 1) == '#';
            case DOWN:
                return robot.y != field.length - 1 && field[robot.y + 1].charAt(robot.x) == '#';
            case LEFT:
                return robot.x != 0 && field[robot.y].charAt(robot.x - 1) == '#';
            default:
                throw new IllegalStateException("Illegal direction: " + direction);
        }
    }

    protected String getPath() {
        String[] field = getScaffoldView();
        StringBuilder sb = new StringBuilder();
        Point<Integer> robot = getRobotLocation();
        int straightCount = 0;
        DIRECTION direction = getRobotDirection();
        while (true) {
            switch (direction) {
                case UP:
                    if (canGo(robot, direction)) {
                        straightCount++;
                        robot.y--;
                    } else if (canGo(robot, DIRECTION.LEFT)) {
                        sb.append(straightCount).append(",L,");
                        straightCount = 0;
                        direction = DIRECTION.LEFT;
                    } else if (canGo(robot, DIRECTION.RIGHT)) {
                        sb.append(straightCount).append(",R,");
                        straightCount = 0;
                        direction = DIRECTION.RIGHT;
                    } else {
                        String res = sb.append(straightCount).toString();
                        if (res.startsWith("0,")) return res.substring(2);
                        return res;
                    }
                    break;
                case RIGHT:
                    if (canGo(robot, direction)) {
                        straightCount++;
                        robot.x++;
                    } else if (canGo(robot, DIRECTION.UP)) {
                        sb.append(straightCount).append(",L,");
                        straightCount = 0;
                        direction = DIRECTION.UP;
                    } else if (canGo(robot, DIRECTION.DOWN)) {
                        sb.append(straightCount).append(",R,");
                        straightCount = 0;
                        direction = DIRECTION.DOWN;
                    } else {
                        String res = sb.append(straightCount).toString();
                        if (res.startsWith("0,")) return res.substring(2);
                        return res;
                    }
                    break;
                case DOWN:
                    if (canGo(robot, direction)) {
                        straightCount++;
                        robot.y++;
                    } else if (canGo(robot, DIRECTION.RIGHT)) {
                        sb.append(straightCount).append(",L,");
                        straightCount = 0;
                        direction = DIRECTION.RIGHT;
                    } else if (canGo(robot, DIRECTION.LEFT)) {
                        sb.append(straightCount).append(",R,");
                        straightCount = 0;
                        direction = DIRECTION.LEFT;
                    } else {
                        String res = sb.append(straightCount).toString();
                        if (res.startsWith("0,")) return res.substring(2);
                        return res;
                    }
                    break;
                case LEFT:
                    if (canGo(robot, direction)) {
                        straightCount++;
                        robot.x--;
                    } else if (canGo(robot, DIRECTION.DOWN)) {
                        sb.append(straightCount).append(",L,");
                        straightCount = 0;
                        direction = DIRECTION.DOWN;
                    } else if (canGo(robot, DIRECTION.UP)) {
                        sb.append(straightCount).append(",R,");
                        straightCount = 0;
                        direction = DIRECTION.UP;
                    } else {
                        String res = sb.append(straightCount).toString();
                        if (res.startsWith("0,")) return res.substring(2);
                        return res;
                    }
                    break;
            }
        }
    }

    enum DIRECTION {
        UP, DOWN, LEFT, RIGHT
    }
}
