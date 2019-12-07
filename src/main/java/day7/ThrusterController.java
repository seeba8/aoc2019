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
        for(int i = 0; i < phaseSetting.length; i++) {
            a = new Amplifier(intcode.clone(),phaseSetting[i], output);
            output = a.run();
        }
        return output;
    }

    public static void main(String[] args) {
        int[] intcode;
        try {
            intcode = Input.getIntArrayFromSingleLine("day7.txt",",");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        int[] phaseSetting = new int[]{0,1,2,3,4};
        int[][] phasePermutations = Utils.getPermutations(phaseSetting);
        int max = Integer.MIN_VALUE;
        for(int[] permutation : phasePermutations) {
            max = Math.max(thrustSignal(intcode.clone(), permutation),max);
        }
        System.out.printf("The highest signal possible is %d\n", max);

    }
}
