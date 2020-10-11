package day13;

import org.junit.jupiter.api.Test;
import utils.Input;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestArcadeComputer {

    @Test
    public void testCountTiles() {
        long[] input;
        try {
            input = Input.getLongArrayFromSingleLine("day13.txt", ",");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        ArcadeComputer ac = new ArcadeComputer(input);
        ac.run();
        assertEquals(83, ac.countTiles(Tile.WALL));
    }

    @Test
    public void testPrintScreen() {
        ArcadeComputer ac = new ArcadeComputer(new long[]{});
        ac.output(0); ac.output(0); ac.output(1); //(0,0) WALL
        ac.output(1); ac.output(0); ac.output(1); //(1,0) WALL
        ac.output(2); ac.output(0); ac.output(1); //(2,0) WALL
        ac.output(0); ac.output(1); ac.output(1); //(0,1) WALL
        ac.output(0); ac.output(2); ac.output(1); //(0,2) WALL
        ac.output(2); ac.output(2); ac.output(4); //(2,2) BALL
        assertEquals("XXX\nX  \nX O", ac.printScreen());
    }
}
