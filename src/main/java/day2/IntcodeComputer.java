package day2;

import utils.Input;

import java.io.IOException;
import java.net.URISyntaxException;

public class IntcodeComputer {
    protected int[] programme;
    protected int instrPointer = 0;

    public IntcodeComputer(int[] input) {
        this.programme = input;
    }

    public static void main(String[] args) {
        try {
            int[] input = Input.getIntArrayFromSingleLine("day2.txt", ",");
            IntcodeComputer ic = new IntcodeComputer(input.clone());
            ic.parseInput(12, 2);
            System.out.println("At position 0: " + ic.run());

            ic = new IntcodeComputer(input.clone());
            System.out.println("100*noun+verb = " + ic.findInputforOutput(19690720));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public int run() {
        instrPointer = 0;
        while (true) {
            if (!executeOpcode()) break;
        }
        ;
        return this.programme[0];
    }

    /**
     * @return false when opcode 99 is reached
     */
    protected boolean executeOpcode() {
        switch (programme[instrPointer]) {
            case 1:
                programme[programme[instrPointer + 3]] = programme[programme[instrPointer + 1]] + programme[programme[instrPointer + 2]];
                break;
            case 2:
                programme[programme[instrPointer + 3]] = programme[programme[instrPointer + 1]] * programme[programme[instrPointer + 2]];
                break;
            case 99:
                return false;
            default:
                throw new IndexOutOfBoundsException("Unknown opcode");
        }
        instrPointer += 4;
        return true;
    }

    public void parseInput(int noun, int verb) {
        programme[1] = noun;
        programme[2] = verb;
    }

    public int findInputforOutput(int desiredOutput) {
        int[] initialMemory = programme.clone();
        for (int noun = 0; noun < 99; noun++) {
            for (int verb = 0; verb < 99; verb++) {
                programme = initialMemory.clone();
                parseInput(noun, verb);
                if (run() == desiredOutput) {
                    return 100 * noun + verb;
                }
            }
        }
        throw new IllegalStateException("Did not find any suitable noun and verb :-(");
    }
}
