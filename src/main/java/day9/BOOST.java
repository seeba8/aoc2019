package day9;

import day5.AdvancedIntcodeComputer;
import utils.Input;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class BOOST {
    public static void main(String[] args) {
        long[] input;
        try {
            input = Input.getLongArrayFromSingleLine("day9.txt", ",");

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        AdvancedIntcodeComputer c = new AdvancedIntcodeComputer(input, 1);
        c.run();
    }
}
