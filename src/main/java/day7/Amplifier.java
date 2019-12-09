package day7;

import day5.AdvancedIntcodeComputer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Amplifier extends AdvancedIntcodeComputer {
    private int out;
    private int secondInput = 0;

    public Amplifier(int[] programme, int phase) {
        super(programme, phase);
    }

    /**
     * @param programme programme intcode
     * @param phase     Phase setting. In the five amplifiers, each value between 0 and 4 must be used exactly once
     * @param input     Output from the previous amplifier, or 0 in the first round
     */
    public Amplifier(int[] programme, int phase, int input) {
        super(programme, phase);
        secondInput = input;
    }

    @Override
    protected void output(long value) {
        out = (int)value;
    }

    @Override
    public int run() {
        super.run();
        return out;
    }

    @Override
    protected long getInput() {
        int buf = (int)super.getInput();
        setInput(secondInput);
        return buf;
    }

}
