package day6;

import utils.Input;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OrbitMap {
    private Map<String, Planet> planets = new HashMap<>();

    public OrbitMap(String[] map) {
        for (String pairString : map) {
            String[] pair = Arrays.stream(pairString.split("\\)")).map(String::trim).toArray(String[]::new);
            Planet left = planets.computeIfAbsent(pair[0], p -> new Planet(pair[0]));
            Planet right = planets.computeIfAbsent(pair[1], p -> new Planet(pair[1]));
            planets.put(right.getName(), right);
            left.addSatellite(right);
        }
    }

    public static void main(String[] args) {
        OrbitMap map;

        try {
            map = new OrbitMap(Input.getLines("day6.txt"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        System.out.printf("Total number of direct and indirect orbits is %d\n", map.getTotalNumberOfOrbits());
        System.out.printf("Number of orbital transfers needed is %d\n", map.getPlanet("YOU")
                .getNumberOfTransfersTo(map.getPlanet("SAN")));
    }

    public Planet getPlanet(String name) {
        return planets.get(name);
    }

    public int getTotalNumberOfOrbits() {
        return planets.values().stream().mapToInt(Planet::getNumberOfOrbits).sum();
    }

    public Planet getCenterOfMass() {
        return planets.get("COM");
    }
}
