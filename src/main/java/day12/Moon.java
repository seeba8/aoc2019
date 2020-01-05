package day12;

import utils.Point3D;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Moon {
    Point3D<Integer> position;
    Point3D<Integer> velocity = new Point3D<>(0, 0, 0);

    public Moon(Point3D<Integer> position) {
        this.position = position;
    }

    public Moon(String strMoon) {
        Pattern p = Pattern.compile("^<x=(-?\\d+), y=(-?\\d+), z=(-?\\d+)>$");
        Matcher matcher = p.matcher(strMoon);
        matcher.find();
        position = new Point3D<Integer>(Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(3)));
    }

    @Override
    public String toString() {
        return String.format("pos=%s, vel=%s", position.toString(), velocity.toString());
    }

    public void applyVelocity() {
        position.x += velocity.x;
        position.y += velocity.y;
        position.z += velocity.z;
    }

    public int getTotalEnergy() {
        return getPotentialEnergy() * getKineticEnergy();
    }

    private int getKineticEnergy() {
        return Math.abs(velocity.x) + Math.abs(velocity.y) + Math.abs(velocity.z);
    }

    private int getPotentialEnergy() {
        return Math.abs(position.x) + Math.abs(position.y) + Math.abs(position.z);
    }
}
