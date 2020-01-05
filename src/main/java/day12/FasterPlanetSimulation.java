package day12;

import utils.Input;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class FasterPlanetSimulation {
    final int NUM_MOONS;
    final int NUM_AXES = 3;
    /**
     * Every 6 Integers starts a new Moon. Each moon has 3 position coordinates and 3 velocity coordinates: <br/>
     * (pos_x, pos_y, pos_z, vel_x, vel_y, vel_z)
     */
    int[] state;

    FasterPlanetSimulation(String[] input) {
        NUM_MOONS = input.length;
        state = new int[NUM_MOONS * NUM_AXES * 2];
        Pattern p = Pattern.compile("^<x=(-?\\d+), y=(-?\\d+), z=(-?\\d+)>$");
        for (int i = 0; i < NUM_MOONS; i++) {
            String strMoon = input[i];
            Matcher matcher = p.matcher(strMoon);
            if (!matcher.find()) throw new IllegalArgumentException("This is no moon: " + strMoon);
            for (int j = 0; j < 3; j++) {
                state[6 * i + 2 * j] = Integer.parseInt(matcher.group(j + 1));
                state[6 * i + 2 * j + 1] = 0;
            }
        }
    }

    public static void main(String[] args) {
        String[] input;
        try {
            input = Input.getLines("day12.txt");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        FasterPlanetSimulation j = new FasterPlanetSimulation(input);
        j.simulate(1000);
        System.out.printf("Total energy after 1000 steps is %d", j.getTotalEnergy());
    }

    void simulate(long howOften) {
        for (long i = 0; i < howOften; i++) {
            if (i % 100_000_000 == 0 && i != 0) System.out.println(i);
            applyGravity();
            applyVelocity();
        }
    }

    void simulate() {
        simulate(1);
    }

    private void applyGravity() {
        for (int i = 0; i < NUM_MOONS - 1; i++) {
            for (int j = i + 1; j < NUM_MOONS; j++) {
                for (int axis = 0; axis < 3; axis++) {
                    if (state[6 * i + 2 * axis] < state[6 * j + 2 * axis]) {
                        state[6 * i + 2 * axis + 1]++;
                        state[6 * j + 2 * axis + 1]--;
                    } else if (state[6 * i + 2 * axis] > state[6 * j + 2 * axis]) {
                        state[6 * i + 2 * axis + 1]--;
                        state[6 * j + 2 * axis + 1]++;
                    }

                }
            }
        }
    }

    private void applyVelocity() {
        for (int i = 0; i < NUM_MOONS; i++) {
            for (int axis = 0; axis < 3; axis++) {
                state[6 * i + 2 * axis] += state[6 * i + 2 * axis + 1];
            }
        }
    }

    int getTotalEnergy() {
        int total = 0;

        /*for (int i = 0; i < NUM_MOONS; i++) {
            total += Arrays.stream(position[i]).mapToInt(Integer::intValue).map(Math::abs).sum()
                    * Arrays.stream(velocity[i]).mapToInt(Integer::intValue).map(Math::abs).sum();
            Arrays.stream(state).mapToInt(Integer::intValue).
            // Arrays.stream(position[i]).map(Math::abs).sum() * Arrays.stream(velocity[i]).map(Math::abs).sum();
        }*/
        for (int moon = 0; moon < NUM_MOONS; moon++) {
            int totalPos = 0;
            int totalVel = 0;
            for (int axis = 0; axis < NUM_AXES; axis++) {
                totalPos += Math.abs(state[6 * moon + 2 * axis]);
                totalVel += Math.abs(state[6 * moon + 2 * axis + 1]);
            }
            total += totalPos * totalVel;
        }
        return total;
    }

    long getNumberOfStepsUntilRepeat() {
        Set<List<Integer>> states = new HashSet<>();
        int[] currentState;
        long numSteps = 0;
        while (true) {

            simulate(1);
            currentState = Arrays.copyOf(state, state.length);
            // int totalEnergy = getTotalEnergy();
            if (!states.add(Arrays.stream(currentState).boxed().collect(Collectors.toList()))) { // returns false if the set already contains the element
                return numSteps;
            }
            numSteps++;
            if(numSteps % 1_000_000 == 0) {
                System.out.printf("%,d\n", numSteps);
            }
        }
    }
}
