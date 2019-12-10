package day10;

import utils.Point;

import java.util.Collection;
import java.util.Set;

public class Asteroid extends Point<Integer> {

    public Asteroid(Integer x, Integer y) {
        super(x, y);
    }

    public double getAngle(Asteroid other) {
        return Math.PI - Math.atan2(other.x - this.x, other.y - this.y);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    public Asteroid getClosest(Collection<Asteroid> nextAngle) {
        Asteroid closest = null;
        for (Asteroid x : nextAngle) {
            if (closest == null || this.getDistanceTo(x) < this.getDistanceTo(closest)) {
                closest = x;
            }
        }
        return closest;
    }

    public double getDistanceTo(Asteroid other) {
        return Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
    }
}
