package day5;

import day2.IntcodeComputer;
import utils.Input;

import java.io.IOException;
import java.net.URISyntaxException;

public class AdvancedIntcodeComputer extends IntcodeComputer {
    protected int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }

    private int input;

    public AdvancedIntcodeComputer(int[] programme, int input) {
        super(programme);
        this.input = input;
    }

    @Override
    protected boolean executeOpcode() {
        int opcode = programme[instrPointer] % 100;
        boolean param1IsPosition = (programme[instrPointer] % 1000 - programme[instrPointer] % 100) == 0;
        boolean param2IsPosition = (programme[instrPointer] % 10000 - programme[instrPointer] % 1000) == 0;
        // boolean param3IsPosition = (programme[instrPointer] % 100000 - programme[instrPointer] % 10000) == 0;
        switch (opcode) {
            case 1:
                programme[programme[instrPointer + 3]] = getParam1(param1IsPosition) + getParam2(param2IsPosition);
                instrPointer += 4;
                break;
            case 2:
                programme[programme[instrPointer + 3]] = getParam1(param1IsPosition) * getParam2(param2IsPosition);
                instrPointer += 4;
                break;
            case 3:
                programme[programme[instrPointer + 1]] = getInput();
                instrPointer += 2;
                break;
            case 4:
                output(getParam1(param1IsPosition));
                instrPointer += 2;
                break;
            case 5:
                if (getParam1(param1IsPosition) != 0) {
                    instrPointer = getParam2(param2IsPosition);
                } else {
                    instrPointer += 3;
                }
                break;
            case 6:
                if (getParam1(param1IsPosition) == 0) {
                    instrPointer = getParam2(param2IsPosition);
                } else {
                    instrPointer += 3;
                }
                break;
            case 7:
                programme[programme[instrPointer + 3]] =
                        (getParam1(param1IsPosition) < getParam2(param2IsPosition) ? 1 : 0);
                instrPointer += 4;
                break;
            case 8:
                programme[programme[instrPointer + 3]] =
                        (getParam1(param1IsPosition) == getParam2(param2IsPosition) ? 1 : 0);
                instrPointer += 4;
                break;
            case 99:
                return false;
            default:
                throw new IndexOutOfBoundsException("Unknown opcode");
        }
        return true;
    }

    protected void output(int value) {
        System.out.println(value);
    }

    private int getParam1(boolean param1IsPosition) {
        if (param1IsPosition) return programme[programme[instrPointer + 1]];
        return programme[instrPointer + 1];
    }

    private int getParam2(boolean param2IsPosition) {
        if (param2IsPosition) return programme[programme[instrPointer + 2]];
        return programme[instrPointer + 2];
    }

    public static void main(String[] args) {
        int[] input;
        try {
            input = Input.getIntArrayFromSingleLine("day5.txt", ",");

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Diagnostic code of Aircon: ");
        AdvancedIntcodeComputer c = new AdvancedIntcodeComputer(input.clone(), 1);
        c.run();

        System.out.println("Diagnostic code of thermal radiator controller:");
        c = new AdvancedIntcodeComputer(input.clone(), 5);
        c.run();
    }
}
