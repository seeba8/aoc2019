package day6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Planet {
    private List<Planet> satellites = new ArrayList<>();
    private String name;
    private Planet star;

    public Planet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /*public Planet getSatelliteByName(String name) {
        for (Planet p : satellites) {
            if (p.getName().equals(name)) {
                return p;
            }
            Planet child = p.getSatelliteByName(name);
            if (child != null) return child;
        }
        return null;
    }*/

    public Planet getStar() {
        return star;
    }

    public void addSatellite(Planet p) {
        satellites.add(p);
        p.star = this;
    }

    public int getNumberOfTransfersTo(Planet p) {
        List<Planet> ownPathToCOM = getPathToCOM();
        List<Planet> otherPathToCOM = p.getPathToCOM();
        Collections.reverse(otherPathToCOM);
        Collections.reverse(ownPathToCOM);
        int i = 0;
        while (ownPathToCOM.get(i) == otherPathToCOM.get(i)) {
            i++;
        }
        // i-1 is last common orbit
        i--;
        return (ownPathToCOM.size() - 1 - i) + (otherPathToCOM.size() - 1 - i);
    }

    public List<Planet> getPathToCOM() {
        List<Planet> path = new ArrayList<>();
        if (this.star == null) return path;
        path.add(this.star);
        path.addAll(this.star.getPathToCOM());
        return path;
    }

    public int getNumberOfDirectSatellites() {
        return satellites.size();
    }

    /*public int getNumberOfIndirectSatellites() {
        int s = getNumberOfDirectSatellites();
        for (Planet p : satellites) {
            s += p.getNumberOfIndirectSatellites();
        }
        return s;
    }*/

    public int getNumberOfOrbits() {
        if (star == null) return 0;
        return 1 + star.getNumberOfOrbits();
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
