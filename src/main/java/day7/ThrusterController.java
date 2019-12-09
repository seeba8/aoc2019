package day7;

import utils.Input;
import utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;

public class ThrusterController {
    public static int thrustSignal(int[] intcode, int[] phaseSetting) {
        int output = 0;
        Amplifier a;
        for (int i = 0; i < phaseSetting.length; i++) {
            a = new Amplifier(intcode.clone(), phaseSetting[i], output);
            output = (int)a.run();
        }
        return output;
    }

    public static int thrustSignal(int[] intcode, int[] phaseSetting, boolean feedbackLoopMode) {
        if (!feedbackLoopMode) return thrustSignal(intcode, phaseSetting);
        FeedbackLoopAmplifier[] amplifiers = new FeedbackLoopAmplifier[phaseSetting.length];
        FeedbackLoopAmplifier prev = null;
        for (int i = phaseSetting.length - 1; i > -1; i--) {
            amplifiers[i] = new FeedbackLoopAmplifier(intcode.clone(), phaseSetting[i], prev);
            prev = amplifiers[i];
        }
        amplifiers[phaseSetting.length - 1].setNextAmp(amplifiers[0]);
        amplifiers[0].setInput(0);
        int currentAmp = 0;
        int output = 0;
        int ampsFinished = 0;
        while (ampsFinished < phaseSetting.length) {
            while (true) {
                if (!amplifiers[currentAmp].executeOpcode()) {
                    if (amplifiers[currentAmp].isFinished()) ampsFinished++;
                    break;
                }
            }
            ;
            currentAmp = (currentAmp + 1) % phaseSetting.length;
        }
        return (int)amplifiers[0].getInput();
    }

    public static void main(String[] args) {
        int[] intcode;
        try {
            intcode = Input.getIntArrayFromSingleLine("day7.txt", ",");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        int[] phaseSetting = new int[]{0, 1, 2, 3, 4};
        int[][] phasePermutations = Utils.getPermutations(phaseSetting);
        int max = Integer.MIN_VALUE;
        for (int[] permutation : phasePermutations) {
            max = Math.max(thrustSignal(intcode.clone(), permutation), max);
        }
        System.out.printf("The highest signal possible is %d\n", max);

        phaseSetting = new int[]{5, 6, 7, 8, 9};
        phasePermutations = Utils.getPermutations(phaseSetting);
        max = Integer.MIN_VALUE;
        for (int[] permutation : phasePermutations) {
            max = Math.max(thrustSignal(intcode.clone(), permutation, true), max);
        }
        System.out.printf("The highest signal possible with feedback loop is %d\n", max);
    }
}
