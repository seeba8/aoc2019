package day12;

import utils.Input;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Jupiter {
    private Moon[] moons;

    public Jupiter(String[] strMoons) {
        moons = new Moon[strMoons.length];
        for (int i = 0; i < strMoons.length; i++) {
            moons[i] = new Moon(strMoons[i]);
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
        Jupiter j = new Jupiter(input);
        j.simulate(1000);
        System.out.printf("Total energy after 1000 steps is %d", j.getTotalEnergy());
    }

    public void simulate() {
        simulate(1);
    }

    public void simulate(long howOften) {
        for (long i = 0; i < howOften; i++) {
            if(i % 100_000 == 0 && i > 0) System.out.println(i);
            applyGravity();
            applyVelocity();
        }
    }

    protected void applyGravity() {
        for (int i = 0; i < moons.length - 1; i++) {
            for (int j = i + 1; j < moons.length; j++) {
                Moon a = moons[i];
                Moon b = moons[j];
                for (int axis = 0; axis < 3; axis++) {
                    if (a.position.getAxis(axis) < b.position.getAxis(axis)) {
                        a.velocity.setAxis(axis, a.velocity.getAxis(axis) + 1);
                        b.velocity.setAxis(axis, b.velocity.getAxis(axis) - 1);
                    } else if (a.position.getAxis(axis) > b.position.getAxis(axis)) {
                        a.velocity.setAxis(axis, a.velocity.getAxis(axis) - 1);
                        b.velocity.setAxis(axis, b.velocity.getAxis(axis) + 1);
                    }
                }
            }
        }
    }

    protected void applyVelocity() {
        for (Moon moon : moons) {
            moon.applyVelocity();
        }
    }

    public Moon getMoon(int index) {
        return moons[index];
    }

    public int getTotalEnergy() {
        return Arrays.stream(moons).mapToInt(Moon::getTotalEnergy).sum();
    }

    @Override
    public String toString() {
        return Arrays.stream(moons)
                .map(x -> x.toString() + "\n")
                .collect(Collectors.joining("\n"));
    }

}
