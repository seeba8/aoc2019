package day8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestImageLayer {

    @Test
    public void testLayerData() {
        Image i = new Image(3, 2);
        i.setImageData(new int[]{
                1, 2, 3, 4, 5, 6,
                7, 8, 9, 0, 1, 2
        });
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, i.getLayer(0).getPixels());
        assertArrayEquals(new int[]{7, 8, 9, 0, 1, 2}, i.getLayer(1).getPixels());

    }

    @Test
    public void testChecksum() {
        Image i = new Image(3, 2);
        i.setImageData(new int[]{
                1, 2, 3, 4, 5, 6,
                7, 8, 9, 0, 1, 2
        });
        assertEquals(1, i.getChecksum());

        i = new Image(3, 2);
        i.setImageData(new int[]{
                0, 0, 1, 1, 2, 2,
                1, 1, 2, 2, 2, 4
        });
        assertEquals(6, i.getChecksum());
    }
}
