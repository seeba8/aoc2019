package day1;

import utils.Input;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class LaunchPreparation {
    static int getFuelRequirement(int mass) {
        return (mass / 3) - 2;
    }

    static int getHolisticFuelRequirement(int mass) {
        int sum = 0;
        while (getFuelRequirement(mass) > 0) {
            mass = getFuelRequirement(mass);
            sum += mass;
        }
        return sum;
    }

    public static void main(String[] args) {
        try {
            int fuelRequirementSum = Arrays.stream(Input.getIntegerArray("day1.txt"))
                    .map(LaunchPreparation::getFuelRequirement)
                    .sum();
            System.out.println("Sum of fuel requirements: " + fuelRequirementSum);

            fuelRequirementSum = Arrays.stream(Input.getIntegerArray("day1.txt"))
                    .map(LaunchPreparation::getHolisticFuelRequirement)
                    .sum();
            System.out.println("Sum of holistic fuel requirements: " + fuelRequirementSum);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
