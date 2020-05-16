package day16;

import utils.Input;
import utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FFT {
    private int[] signal;
    private int[] basePattern = new int[]{0, 1, 0, -1};
    private int part2Offset = 0;

    FFT(int[] inputSignal) {
        this.signal = inputSignal;
        for (int i = 0; i < 7; i++) {
            part2Offset += (signal.length > i ? signal[i] : 0) * Math.pow(10, 6 - i);
        }
    }

    FFT(int[] inputSignal, int repeat) {
        this(inputSignal);
        signal = new int[inputSignal.length * repeat];
        for (int i = 0; i < repeat; i++) {
            System.arraycopy(inputSignal, 0, signal, inputSignal.length * i, inputSignal.length);
        }
    }

    FFT(int[] inputSignal, int[] basePattern) {
        this(inputSignal);
        this.basePattern = basePattern;
    }

    static FFT fromString(String inputSignal) {
        return FFT.fromString(inputSignal, 1);
    }

    static FFT fromString(String inputSignal, int repeat) {
        return new FFT(Arrays.stream(inputSignal.split("")).mapToInt(Integer::parseInt).toArray(), repeat);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        FFT fft = new FFT(Input.getIntArrayFromSingleLine("day16.txt", ""));
        fft.calculatePhases(100);
        System.out.println(Arrays.stream(fft.getSliceAtOffset(0, 8))
                .mapToObj(Integer::toString)
                .collect(Collectors.joining()));
    }

    int[] getSliceAtOffset(int offset, int length) {
        return Arrays.copyOfRange(signal, offset, offset + length);
    }

    int getPart2Offset() {
        return part2Offset;
    }

    int[] getSignal() {
        return signal;
    }

    int[] calculatePhases(int numberOfPhases) {
        for (int i = 0; i < numberOfPhases; i++) {
            calculateNextPhase();
            System.out.println(Arrays.toString(getSliceAtOffset(0, 8)));
        }
        return signal;
    }

    int[] calculateNextPhase() {
        for (int i = 0; i < signal.length; i++) {
            int[] pattern = getPattern(i);
            signal[i] = Math.abs(Utils.multiplyAndAdd(signal, pattern) % 10);
        }
        return signal;
    }

    int[] getPattern(int elementPosition) {
        /*List<Integer> pattern = new ArrayList<>(signal.length);
        int patternIndex = 0;
        int repeatsIndex = 0;

        // skip first value of actual pattern
        repeatsIndex++;
        if (repeatsIndex > elementPosition) {// repeated often enough
            repeatsIndex = 0;
            patternIndex++;
        }


        while (pattern.size() != signal.length) {
            pattern.add(basePattern[patternIndex % basePattern.length]);
            repeatsIndex++;
            if (repeatsIndex > elementPosition) {// repeated often enough
                repeatsIndex = 0;
                patternIndex++;
            }
        }
        return pattern.stream().mapToInt(Integer::intValue).toArray();*/
        int[] pattern = new int[signal.length];
        int patIndex = 0;
        for (int i = 0; i < signal.length; i += elementPosition + 1, patIndex++) {
            Arrays.fill(pattern, i, Math.min(i + elementPosition + 1, signal.length), basePattern[patIndex % basePattern.length]);
        }
        System.arraycopy(pattern, 1, pattern, 0, signal.length - 1);
        pattern[signal.length - 1] = basePattern[((signal.length / (elementPosition + 1)) % basePattern.length)];
        return pattern;
    }
}
