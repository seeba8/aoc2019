package day7;

import day5.AdvancedIntcodeComputer;

import java.util.Arrays;

public class Amplifier extends AdvancedIntcodeComputer {
    private int out;
    private int secondInput = 0;

    public Amplifier(int[] programme, int phase) {
        super(Arrays.stream(programme).mapToLong(Long::valueOf).toArray(), phase);
    }

    /**
     * @param programme programme intcode
     * @param phase     Phase setting. In the five amplifiers, each value between 0 and 4 must be used exactly once
     * @param input     Output from the previous amplifier, or 0 in the first round
     */
    public Amplifier(int[] programme, int phase, int input) {
        super(Arrays.stream(programme).mapToLong(Long::valueOf).toArray(), phase);
        secondInput = input;
    }

    @Override
    protected void output(long value) {
        out = (int)value;
    }

    @Override
    public long run() {
        super.run();
        return out;
    }

    @Override
    protected long getInput() {
        int buf = (int)input;
        setInput(secondInput);
        return buf;
    }

}
