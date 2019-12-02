package day2;

import utils.Input;

import java.io.IOException;
import java.net.URISyntaxException;

public class IntcodeComputer {
    int[] programme;

    public IntcodeComputer(int[] input) {
        this.programme = input;
    }

    public int run() {
        executeOpcode(0);
        return this.programme[0];
    }

    private void executeOpcode(int position) {
        switch (programme[position]) {
            case 1:
                programme[programme[position + 3]] = programme[programme[position + 1]] + programme[programme[position + 2]];
                break;
            case 2:
                programme[programme[position + 3]] = programme[programme[position + 1]] * programme[programme[position + 2]];
                break;
            case 99:
                return;
            default:
                throw new IndexOutOfBoundsException("Unknown opcode");
        }
        executeOpcode(position + 4);
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
}
