package day12;

import utils.Input;
import utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class FasterPlanetSimulation {
    final int NUM_MOONS;
    final int NUM_AXES = 3;
    /**
     * Every 6 Integers starts a new Moon. Each moon has 3 position coordinates and 3 velocity coordinates: <br/>
     * (pos_x, vel_x,  pos_y, vel_y, pos_z, vel_z)
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
        System.out.printf("Total energy after 1000 steps is %d\n", j.getTotalEnergy());
        j = new FasterPlanetSimulation(input);
        System.out.println("j.getNumberOfStepsUntilRepeat() = " + j.getNumberOfStepsUntilRepeat());
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
        int[] initialState = state.clone();
        long[] phaseLengths = new long[NUM_AXES];

        for(int axis = 0; axis < NUM_AXES; axis++) {
            Set<List<Integer>> axisStates = new HashSet<>();
            long count = 0;
            state = initialState.clone();

            while(addAxisState(axisStates, axis)) {
                simulate();
                count++;
            }
            phaseLengths[axis] = count;
        }
        return Utils.lcm(phaseLengths);
    }

    private boolean addAxisState(Set<List<Integer>> axisStates, int axis) {
        Integer[] axisState = new Integer[2 * NUM_MOONS];
        for (int i = 0; i < NUM_MOONS * 2; i += 2) {
            axisState[i] = state[2 * axis + NUM_AXES * i];
            axisState[i + 1] = state[2 * axis + NUM_AXES * i + 1];
        }
        return axisStates.add(Arrays.asList(axisState));
    }

}
