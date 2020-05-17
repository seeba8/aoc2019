package day16;

import utils.Input;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class FFT {
    private int[] signal;
    private int part2Offset = 0;
    //private int[][] patternCache;

    FFT(int[] inputSignal) {
        this.signal = inputSignal;
        calculateOffset();
    }

    FFT(int[] inputSignal, int repeat) {
        this(inputSignal);
        this.signal = new int[inputSignal.length * repeat];
        for (int i = 0; i < repeat; i++) {
            System.arraycopy(inputSignal, 0, signal, inputSignal.length * i, inputSignal.length);
        }
    }

    static FFT fromString(String inputSignal) {
        return FFT.fromString(inputSignal, 1);
    }

    static FFT fromString(String inputSignal, int repeat) {
        return new FFT(Arrays.stream(inputSignal.split("")).mapToInt(Integer::parseInt).toArray(), repeat);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        FFT fft = FFT.fromString(Input.getLines("day16.txt")[0]);
        fft.calculatePhases(100);
        System.out.println(Arrays.toString(fft.getSliceAtOffset(0, 8)));
    }

    private void calculateOffset() {
        for (int i = 0; i < 7; i++) {
            part2Offset += (signal.length > i ? signal[i] : 0) * Math.pow(10, 6 - i);
        }
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

    void calculatePhases(int numberOfPhases) {
        for (int i = 0; i < numberOfPhases; i++) {
            calculateNextPhase();
            System.out.println(i);
            //System.out.println(Arrays.toString(getSliceAtOffset(0, 8)));
        }
    }

    int[] calculateNextPhase() {
        int[] basePattern = new int[]{0, 1, 0, -1};
        //if (patternCache == null) fillPatternCache();
        for (int i = 0; i < signal.length; i++) {
            int sum = 0;
            int patIndex = 0;
            for (int j = 0; j < signal.length; j++) {
                if ((j + 1) % (i + 1) == 0) patIndex = (patIndex + 1) % basePattern.length;
                //sum += signal[j] * patternCache[i][j % patternCache[i].length];
                sum += signal[j] * basePattern[patIndex];
            }
            signal[i] = Math.abs(sum % 10);
            //getPattern(i);
            //signal[i] = Math.abs(Utils.dotProductModulo(signal, patternCache[i]) % 10);
            /*int sum = 0;
            for (int j = 0; j < signal.length; j++) {
                sum += signal[j] * (int) Math.sin(((j + 1) / (i + 1)) * Math.PI / 2);
            }
            signal[i] = Math.abs(sum % 10);*/
        }
        return signal;
    }

    /*private void fillPatternCache() {
        System.out.println("started patternCache");
        byte[] basePattern = new byte[]{0, 1, 0, -1};
        patternCache = new int[signal.length][];
        for (int i = 0; i < signal.length; i++) {
            int patIndex = 0;
            patternCache[i] = new int[Math.min(4 * (i + 1), signal.length)];
            for (int j = 0; j < patternCache[i].length; j++) {
                if ((j + 1) % (i + 1) == 0) patIndex = (patIndex + 1) % basePattern.length;
                patternCache[i][j] = basePattern[patIndex];
            }
        }
        System.out.println("finished patternCache");
    }*/
    /*private void fillPatternCache() {
        System.out.println("started patternCache");
        patternCache = new byte[signal.length][];
        for (int i = 0; i < signal.length; i++) {
            patternCache[i] = new byte[Math.min(4 * (i + 1), signal.length)];
            for (int j = 0; j < patternCache[i].length; j++) {
                patternCache[i][j] = (byte) Math.sin(((j + 1) / (i + 1)) * Math.PI / 2);
            }
        }
        System.out.println("finished patternCache");
    }*/

    /*int[] getPattern(int elementPosition) {
//        List<Integer> pattern = new ArrayList<>(signal.length);
//        int patternIndex = 0;
//        int repeatsIndex = 0;
//
//        // skip first value of actual pattern
//        repeatsIndex++;
//        if (repeatsIndex > elementPosition) {// repeated often enough
//            repeatsIndex = 0;
//            patternIndex++;
//        }
//
//
//        while (pattern.size() != signal.length) {
//            pattern.add(basePattern[patternIndex % basePattern.length]);
//            repeatsIndex++;
//            if (repeatsIndex > elementPosition) {// repeated often enough
//                repeatsIndex = 0;
//                patternIndex++;
//            }
//        }
//        return pattern.stream().mapToInt(Integer::intValue).toArray();
        //int[] pattern = new int[signal.length];
        int patIndex = 0;
        for (int i = 0; i < signal.length; i += elementPosition + 1, patIndex++) {
            Arrays.fill(pattern, i, Math.min(i + elementPosition + 1, signal.length), basePattern[patIndex % basePattern.length]);
        }
        System.arraycopy(pattern, 1, pattern, 0, signal.length - 1);
        pattern[signal.length - 1] = basePattern[((signal.length / (elementPosition + 1)) % basePattern.length)];
        return pattern;
    }*/
}
