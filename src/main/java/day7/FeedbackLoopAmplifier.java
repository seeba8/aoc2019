package day7;

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
    protected void output(int value) {
        nextAmp.setInput(value);
    }

    @Override
    protected int getInput() {
        return inputQueue.remove();
    }

    @Override
    public void setInput(int value) {
        inputQueue.add(value);
    }

    @Override
    protected boolean executeOpcode() {
        int opcode = programme[instrPointer] % 100;
        boolean param1IsPosition = (programme[instrPointer] % 1000 - programme[instrPointer] % 100) == 0;
        boolean param2IsPosition = (programme[instrPointer] % 10000 - programme[instrPointer] % 1000) == 0;
        switch (opcode) {
            case 3:
                try {
                    programme[programme[instrPointer + 1]] = getInput();
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
