package day5;

import day2.IntcodeComputer;
import utils.Input;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class AdvancedIntcodeComputer extends IntcodeComputer {
    private long input;
    private long relativeBase = 0;
    private long[] memory;

    public AdvancedIntcodeComputer(long[] programme, long input) {
        super(new int[]{});
        this.memory = programme;
        this.input = input;
    }

    public AdvancedIntcodeComputer(int[] programme, int input) {
        super(programme);
        this.input = input;
        this.memory = Arrays.stream(programme).mapToLong(Long::valueOf).toArray();
    }

    public static void main(String[] args) {
        long[] input;
        try {
            input = Arrays.stream(Input.getIntArrayFromSingleLine("day5.txt", ","))
                    .mapToLong(Long::valueOf)
                    .toArray();

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

    protected long getInput() {
        return input;
    }

    public void setInput(long input) {
        this.input = input;
    }

    protected ParameterMode[] getParameterModes(long instruction) {
        ParameterMode[] parameterModes = new ParameterMode[3];
        for (int i = 0; i < 3; i++) {
            int paramModeInt = (int) ((instruction % Math.pow(10, i + 3) - instruction % Math.pow(10, i + 2)) /
                    Math.pow(10, i + 2));
            switch (paramModeInt) {
                case 0:
                    parameterModes[i] = ParameterMode.POSITION;
                    break;
                case 1:
                    parameterModes[i] = ParameterMode.ABSOLUTE;
                    break;
                case 2:
                    parameterModes[i] = ParameterMode.RELATIVE;
                    break;
                default:
                    throw new IllegalArgumentException("Parameter mode not defined: " + paramModeInt);
            }
        }
        return parameterModes;
    }

    @Override
    protected boolean executeOpcode() {
        int opcode = (int)read(instrPointer) % 100;
        ParameterMode[] parameterModes = getParameterModes(read(instrPointer));
        // boolean param3IsPosition = (programme[instrPointer] % 100000 - programme[instrPointer] % 10000) == 0;
        switch (opcode) {
            case 1:
                setParameter(instrPointer + 3, parameterModes[2], getParameter(instrPointer + 1, parameterModes[0]) +
                        getParameter(instrPointer + 2, parameterModes[1]));
                instrPointer += 4;
                break;
            case 2:
                setParameter(instrPointer + 3, parameterModes[2], getParameter(instrPointer + 1, parameterModes[0]) *
                        getParameter(instrPointer + 2, parameterModes[1]));
                instrPointer += 4;
                break;
            case 3:
                setParameter(instrPointer + 1, parameterModes[0], getInput());
                instrPointer += 2;
                break;
            case 4:
                output(getParameter(instrPointer + 1, parameterModes[0]));
                instrPointer += 2;
                break;
            case 5:
                if (getParameter(instrPointer + 1, parameterModes[0]) != 0) {
                    instrPointer = (int)getParameter(instrPointer + 2, parameterModes[1]);
                } else {
                    instrPointer += 3;
                }
                break;
            case 6:
                if (getParameter(instrPointer + 1, parameterModes[0]) == 0) {
                    instrPointer = (int)getParameter(instrPointer + 2, parameterModes[1]);
                } else {
                    instrPointer += 3;
                }
                break;
            case 7:
                setParameter(instrPointer + 3, parameterModes[2],
                        (getParameter(instrPointer + 1, parameterModes[0]) < getParameter(instrPointer + 2, parameterModes[1]) ? 1 : 0));
                instrPointer += 4;
                break;
            case 8:
                setParameter(instrPointer + 3, parameterModes[2],
                        (getParameter(instrPointer + 1, parameterModes[0]) == getParameter(instrPointer + 2, parameterModes[1]) ? 1 : 0));
                instrPointer += 4;
                break;
            case 9:
                relativeBase += getParameter(instrPointer + 1, parameterModes[0]);
                instrPointer += 2;
                break;
            case 99:
                return false;
            default:
                throw new IndexOutOfBoundsException("Unknown opcode");
        }
        return true;
    }

    protected void setParameter(long instrPointer, ParameterMode parameterMode, long value) {
        switch (parameterMode) {
            case POSITION:
                //programme[programme[instrPointer]] = value;
                write(read(instrPointer), value);
                return;
            case ABSOLUTE:
                throw new IllegalArgumentException("Absolute argument may not be written to!");
            case RELATIVE:
                //programme[programme[instrPointer] + relativeBase] = value;
                write(read(read(instrPointer) + relativeBase), value);
                return;
            default:
                throw new IllegalArgumentException("Parameter mode not defined: " + parameterMode);
        }
    }

    protected long getParameter(long instrPointer, ParameterMode parameterMode) {
        switch (parameterMode) {
            case POSITION:
                //return programme[programme[instrPointer]];
                return read(read(instrPointer));
            case ABSOLUTE:
                //return programme[instrPointer];
                return read(instrPointer);
            case RELATIVE:
                //return programme[programme[instrPointer] + relativeBase];
                return read(read(instrPointer) + relativeBase);
            default:
                throw new IllegalArgumentException("Parameter mode not defined: " + parameterMode);
        }
    }

    protected void output(long value) {
        System.out.println(value);
    }

    protected long read(long position) {
        if(position > memory.length - 1) {
            return 0;
        }
        return memory[(int)position];
    }

    protected void write(long position, long value) {
        if(position > memory.length - 1) {
            memory = Arrays.copyOf(memory, (int)position + 1);
        }
        memory[(int)position] = value;
    }
}

