package day5;

import java.util.Arrays;

public abstract class AdvancedIntcodeComputer {
    protected long input;
    private long relativeBase = 0;
    private long[] memory;
    protected int instrPointer;

    public AdvancedIntcodeComputer(long[] programme, long input) {
        this.memory = programme;
        this.input = input;
    }

    public long run() {
        instrPointer = 0;
        while (true) {
            if (!executeOpcode()) break;
        }
        return this.memory[0];
    }

    protected abstract long getInput();

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

    protected boolean executeOpcode() {
        int opcode = (int) (read(instrPointer) % 100);

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
                    instrPointer = (int) getParameter(instrPointer + 2, parameterModes[1]);
                } else {
                    instrPointer += 3;
                }
                break;
            case 6:
                if (getParameter(instrPointer + 1, parameterModes[0]) == 0) {
                    instrPointer = (int) getParameter(instrPointer + 2, parameterModes[1]);
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
                write(read(instrPointer), value);
                return;
            case ABSOLUTE:
                throw new IllegalArgumentException("Absolute argument may not be written to!");
            case RELATIVE:
                write(read(instrPointer) + relativeBase, value);
                return;
            default:
                throw new IllegalArgumentException("Parameter mode not defined: " + parameterMode);
        }
    }

    private long getParameter(long instrPointer, ParameterMode parameterMode) {
        switch (parameterMode) {
            case POSITION:
                return read(read(instrPointer));
            case ABSOLUTE:
                return read(instrPointer);
            case RELATIVE:
                return read(read(instrPointer) + relativeBase);
            default:
                throw new IllegalArgumentException("Parameter mode not defined: " + parameterMode);
        }
    }

    protected abstract void output(long value);

    protected long read(long position) {
        if (position > memory.length - 1) {
            return 0;
        }
        return memory[(int) position];
    }

    private void write(long position, long value) {
        if (position > memory.length - 1) {
            memory = Arrays.copyOf(memory, (int) position + 1);
        }
        memory[(int) position] = value;
    }
}

