package day15;


import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class TestRepairDroid {

    public void testArrayDeque() {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(1);
        deque.add(2);
        deque.add(3);
        for (Integer integer : deque) {
            System.out.println(integer);
        }
        System.out.println(deque.size());
    }

    @Test
    public void testAddToQueue() {
        RepairDroid rd = new RepairDroid(new long[]{});
        rd.addToQueue(Command.EAST);
        assertArrayEquals(new Command[]{Command.EAST, Command.WEST}, rd.queue.toArray(new Command[0]));
    }

    @Test
    public void testAddToQueueWithPath() {
        RepairDroid rd = new RepairDroid(new long[]{});
        rd.currentPathStack.add(Command.NORTH);
        rd.addToQueue(Command.EAST);
        assertArrayEquals(new Command[]{Command.NORTH, Command.EAST, Command.WEST, Command.SOUTH},
                rd.queue.toArray(new Command[0]));
    }
}
