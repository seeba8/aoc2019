package day10;

import utils.Input;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class AsteroidMap {
    protected List<Asteroid> asteroids;

    public AsteroidMap(String[] map) {
        asteroids = new ArrayList<>();
        for (int y = 0; y < map.length; y++) {
            char[] row = map[y].toCharArray();
            for (int x = 0; x < map[0].length(); x++) {
                if (row[x] == '#') {
                    asteroids.add(new Asteroid(x, y));
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] lines;
        try {
            lines = Input.getLines("day10.txt");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        AsteroidMap a = new AsteroidMap(lines);
        int max = a.asteroids.stream()
                .mapToInt(a::getNumberOfVisibleAsteroids)
                .max()
                .orElseThrow(IllegalStateException::new);
        System.out.printf("%d asteroids can be detected from the best location\n", max);

        Asteroid bestAsteroid = a.getBestAsteroid();
        Asteroid num200 = a.vaporise(bestAsteroid).get(199);
        System.out.printf("Value = %d\n", num200.x * 100 + num200.y);
    }


    public int getNumberOfVisibleAsteroids(Asteroid base) {
        int num = 0;
        Set<Double> angles = new HashSet<>();
        for (Asteroid other : asteroids) {
            if (other.equals(base)) continue;
            if (angles.add(base.getAngle(other))) {
                num++;
            }
        }
        return num;
    }

    public Asteroid getBestAsteroid() {
        int bestNumber = 0;
        Asteroid bestAsteroid = null;
        for (Asteroid base : asteroids) {
            int num = 0;
            Set<Double> angles = new HashSet<>();
            for (Asteroid other : asteroids) {
                if (other.equals(base)) continue;
                if (angles.add(base.getAngle(other))) {
                    num++;
                }
            }
            if (num > bestNumber) {
                bestAsteroid = base;
                bestNumber = num;
            }
        }
        return bestAsteroid;
    }

    public Map<Double, Set<Asteroid>> getAsteroidAngles(Asteroid base) {
        SortedMap<Double, Set<Asteroid>> angles = new TreeMap<>();
        for (Asteroid a : asteroids) {
            if (base.equals(a)) continue;
            double angle = base.getAngle(a);
            if (!angles.containsKey(angle)) {
                angles.put(angle, new HashSet<Asteroid>());
            }
            angles.get(angle).add(a);
        }

        return angles;
    }

    public List<Asteroid> vaporise(Asteroid base) {
        List<Asteroid> vaporised = new ArrayList<>();
        Map<Double, Set<Asteroid>> asteroidAngles = getAsteroidAngles(base);
        Iterator<Set<Asteroid>> iterator = null;
        while (vaporised.size() != asteroids.size() - 1) { // -1 to exclude the base
            if (iterator == null || !iterator.hasNext()) {
                iterator = asteroidAngles.values().iterator();
            }
            Set<Asteroid> nextAngle = iterator.next();
            if (nextAngle.size() == 0) continue;
            Asteroid closest = base.getClosest(nextAngle);
            nextAngle.remove(closest);
            vaporised.add(closest);
        }
        return vaporised;
    }
}
