package day7;

import day5.ParameterMode;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class FeedbackLoopAmplifier extends Amplifier {
    private FeedbackLoopAmplifier nextAmp;
    private Queue<Integer> inputQueue = new LinkedList<>(); // not priorityqueue. priorityqueue is not a FIFO queue
    private boolean isFinished = false;

    public FeedbackLoopAmplifier(int[] intcode, int phase, FeedbackLoopAmplifier nextAmp) {
        super(intcode, phase);
        inputQueue.add(phase);
        this.nextAmp = nextAmp;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setNextAmp(FeedbackLoopAmplifier nextAmp) {
        this.nextAmp = nextAmp;
    }

    @Override
    protected void output(long value) {
        nextAmp.setInput(value);
    }

    @Override
    protected long getInput() {
        return inputQueue.remove();
    }

    @Override
    public void setInput(long value) {
        inputQueue.add((int)value);
    }

    @Override
    protected boolean executeOpcode() {
        int opcode = (int)read(instrPointer) % 100;
        ParameterMode[] parameterModes = getParameterModes(read(instrPointer));
        switch (opcode) {
            case 3:
                try {
                    setParameter(instrPointer + 1, parameterModes[0],(int)getInput());
                } catch (NoSuchElementException e) {
                    return false;
                }
                instrPointer += 2;
                return true;
            case 99:
                isFinished = true;
                return false;
            default:
                return super.executeOpcode();
        }
    }
}
