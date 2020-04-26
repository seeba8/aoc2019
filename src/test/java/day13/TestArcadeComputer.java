package day13;

import org.junit.Test;
import utils.Input;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

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
        assertEquals(286, ac.countTiles(Tile.BLOCK));
    }
}
