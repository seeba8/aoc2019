package day15;

import day5.AdvancedIntcodeComputer;
import utils.Input;
import utils.Point;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class RepairDroid extends AdvancedIntcodeComputer {
    Queue<Command> queue = new ArrayDeque<>();
    Deque<Command> currentPathStack = new ArrayDeque<>();
    boolean getCompleteMap = false;
    private int longestDistance = 0;
    private Set<Point<Integer>> visited = new HashSet<>();
    private Point<Integer> position;


    RepairDroid(long[] programme) {
        super(programme, 0);
    }

    void initialise() {
        position = new Point<>(0, 0);
        visited.add(position.clone());
        for (Command c : Command.values()) {
            addToQueue(c);
        }
    }

    /**
     * Adds Command c to the queue by first following the currentPathStack, assuming we start at the origin.
     * Also adds Commands to go back to the origin afterwards so we do a breadth-first search of the oxygen system
     *
     * @param c
     */
    void addToQueue(Command c) {
        queue.addAll(currentPathStack);
        queue.add(c);
        queue.add(c.reverse());
        Iterator<Command> itr = currentPathStack.descendingIterator();
        while (itr.hasNext()) {
            queue.add(itr.next().reverse());
        }
    }

    @Override
    protected long getInput() {
        if (queue.peek() == null) {
            System.out.println("No further paths possible. Longest distance: " + longestDistance);
            System.exit(0);
        }
        // add the next step to currentPathStack in the assumption that we were able to walk it.
        // it is removed in output() if we were not able to because we hit a wall
        currentPathStack.add(queue.peek());
        return Objects.requireNonNull(queue.poll()).intValue();
    }

    @Override
    protected void output(long value) {
        Status out = Status.fromValue((int) value);
        switch (out) {
            case WALL:
                // remove the last element from currentPathStack as we did not walk that step after all
                Command unsuccessfulCommand = currentPathStack.removeLast();
                // we also need to remove the potential return path if we are in the discovery phase
                // (i.e., if the next step is the exact reverse)
                if (queue.peek() == unsuccessfulCommand.reverse()) {
                    queue.poll();
                }
                break;
            case MOVED:
                doMove();
                break;
            case MOVED_OXYGEN:
                doMove();
                arrived();
        }
    }

    private void doMove() {
        updatePosition();
        if (isBackTracking()) {
            currentPathStack.pollLast();
            currentPathStack.pollLast();
        } else {
            longestDistance = Math.max(longestDistance, currentPathStack.size());
            if(!visited.contains(position)) {
                for (Command c : Command.values()) {
                    if (c != currentPathStack.peekLast().reverse()) { // we don't want to walk back
                        addToQueue(c);
                    }
                }
            }
        }
        visited.add(position.clone());
    }

    private void updatePosition() {
        switch (currentPathStack.peekLast()) {
            case NORTH:
                position.y += 1;
                break;
            case SOUTH:
                position.y -= 1;
                break;
            case EAST:
                position.x += 1;
                break;
            case WEST:
                position.x -= 1;
                break;
            default:
                throw new IllegalStateException("There are only four directions!");
        }
    }

    private boolean isBackTracking() {
        Iterator<Command> commandIterator = currentPathStack.descendingIterator();
        Command current = commandIterator.next();
        return commandIterator.hasNext() && commandIterator.next().reverse() == current;
    }

    private void arrived() {
        if(!getCompleteMap) {
            System.out.println(currentPathStack.size());
            System.exit(0);
        }
        // we are now at the point where we want to start mapping. So we reset the queue and the currentPathStack.
        // then we start from scratch until the queue is empty.
        if(!position.equals(new Point<Integer>(0,0))){
            // we are here for the first time
            System.out.printf("Shortest path to oxygen: %s\n", currentPathStack.size());
            queue.clear();
            visited.clear();
            currentPathStack.clear();
            initialise();
        }

    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        long[] input = Input.getLongArrayFromSingleLine("day15.txt", ",");
        RepairDroid rd = new RepairDroid(input.clone());
        rd.initialise();
        rd.getCompleteMap = true;
        rd.run();
    }
}
