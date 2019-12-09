package day9;

import day5.AdvancedIntcodeComputer;
import utils.Input;

import java.io.IOException;
import java.net.URISyntaxException;

public class BOOST {
    public static void main(String[] args) {
        long[] input;
        try {
            input = Input.getLongArrayFromSingleLine("day9.txt", ",");

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        AdvancedIntcodeComputer c = new AdvancedIntcodeComputer(input.clone(), 1);
        System.out.println("Test mode:");
        c.run();
        c = new AdvancedIntcodeComputer(input, 2);
        System.out.println("Sensor boost mode:");
        c.run();
    }
}
