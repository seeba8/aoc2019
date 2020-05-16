package day16;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestFFT {
    @Test
    public void testGetPattern() {
        FFT fft = new FFT(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        assertArrayEquals(new int[]{0, 0, 1, 1, 1, 0, 0, 0}, fft.getPattern(2));
        assertArrayEquals(new int[]{0, 1, 1, 0, 0, -1, -1, 0,}, fft.getPattern(1));
    }

    @Test
    public void testNextPattern() {
        FFT fft = FFT.fromString("12345678");
        assertArrayEquals(new int[]{4, 8, 2, 2, 6, 1, 5, 8}, fft.calculateNextPhase());
        assertArrayEquals(new int[]{3, 4, 0, 4, 0, 4, 3, 8}, fft.calculateNextPhase());
        assertArrayEquals(new int[]{0, 3, 4, 1, 5, 5, 1, 8}, fft.calculateNextPhase());
        assertArrayEquals(new int[]{0, 1, 0, 2, 9, 4, 9, 8}, fft.calculateNextPhase());
    }

    @Test
    public void testLongerExamples() {
        FFT fft = FFT.fromString("80871224585914546619083218645595");
        fft.calculatePhases(100);
        assertArrayEquals(new int[]{2, 4, 1, 7, 6, 1, 7, 6},
                fft.getSliceAtOffset(0, 8));

        fft = FFT.fromString("19617804207202209144916044189917");
        fft.calculatePhases(100);
        assertArrayEquals(new int[]{7, 3, 7, 4, 5, 4, 1, 8},
                fft.getSliceAtOffset(0, 8));

        fft = FFT.fromString("69317163492948606335995924319873");
        fft.calculatePhases(100);
        assertArrayEquals(new int[]{5, 2, 4, 3, 2, 1, 3, 3},
                fft.getSliceAtOffset(0, 8));
    }

    @Test
    public void testPart2Offset() {
        FFT fft = FFT.fromString("03036732577212944063491565474664");
        assertEquals(303673, fft.getPart2Offset());
    }

    @Test
    public void testFFTWithRepeat() {
        FFT fft = new FFT(new int[]{1, 2, 3}, 3);
        assertArrayEquals(new int[]{1, 2, 3, 1, 2, 3, 1, 2, 3}, fft.getSignal());
    }

    @Test
    public void testExamplePart2() {
        FFT fft = FFT.fromString("03036732577212944063491565474664", 10_000);
        fft.calculatePhases(100);
        assertArrayEquals(new int[]{8,4,4,6,2,0,2,6}, fft.getSliceAtOffset(fft.getPart2Offset(), 8));
    }


}
