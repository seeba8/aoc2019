package day5;

import utils.Input;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class DiagnosticComputer extends AdvancedIntcodeComputer {
    public DiagnosticComputer(long[] programme, long input) {
        super(programme, input);
    }

    public DiagnosticComputer(int[] programme, int input) {
        super(Arrays.stream(programme).mapToLong(Long::valueOf).toArray(), input);
    }

    @Override
    protected long getInput() {
        return input;
    }

    @Override
    protected void output(long value) {
        System.out.println(value);
    }

    public static void main(String[] args) {
        long[] input;
        try {
            input = Input.getLongArrayFromSingleLine("day5.txt", ",");

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Diagnostic code of Aircon: ");
        DiagnosticComputer c = new DiagnosticComputer(input.clone(), 1);
        c.run();

        System.out.println("Diagnostic code of thermal radiator controller:");
        c = new DiagnosticComputer(input.clone(), 5);
        c.run();
    }
}
