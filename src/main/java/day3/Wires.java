package day3;

import utils.Input;
import utils.Point;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Wires {
    private List<Wire> w1;
    private List<Wire> w2;

    public Wires(String[] input1, String[] input2) {
        w1 = new ArrayList<>();
        w2 = new ArrayList<>();

        fillWireList(input1, w1);
        fillWireList(input2, w2);
    }

    private void fillWireList(String[] input, List<Wire> w) {
        int x = 0, y = 0;
        for (String section : input) {
            String direction = section.substring(0, 1).toUpperCase();
            int length = Integer.parseInt(section.substring(1));
            switch (direction) {
                case "U":
                    w.add(new Wire(x, x, y, y + length, direction));
                    y += length;
                    break;
                case "R":
                    w.add(new Wire(x, x + length, y, y, direction));
                    x += length;
                    break;
                case "D":
                    w.add(new Wire(x, x, y - length, y, direction));
                    y -= length;
                    break;
                case "L":
                    w.add(new Wire(x - length, x, y, y, direction));
                    x -= length;
                    break;
                default:
                    throw new IllegalStateException("Unexpected direction: " + direction);
            }

        }
    }

    public int[] getAllIntersectDistances() {
        List<Integer> distances = new ArrayList<>();
        for (Wire first : w1) {
            for (Wire second : w2) {
                if (first.intersects(second)) {
                    int dist = first.intersectDistance(second);
                    if (dist != 0)
                        distances.add(dist);
                }
            }
        }
        return distances.stream().mapToInt(i -> i).toArray();
    }

    public int getMinimumIntersectDistance() {
        return Arrays.stream(getAllIntersectDistances()).min().getAsInt();
    }

    public int getShortestWireLengthIntersection() {
        List<Point<Integer>> intersections = new ArrayList<>();
        int w1Length = 0;
        List<Integer> lengthAtIntersections = new ArrayList<>();
        for (Wire first : w1) {
            int w2Length = 0;
            for (Wire second : w2) {
                if (first.intersects(second)) {
                    Point<Integer> intersect = first.getIntersectPoint(second);
                    if (!intersect.equals(new Point<Integer>(0, 0))) {
                        intersections.add(intersect);
                        lengthAtIntersections.add(
                                w1Length + first.getDistanceToPoint(intersect, true)
                                        + w2Length + second.getDistanceToPoint(intersect, true));
                    }
                }
                w2Length += second.getLength();
            }
            w1Length += first.getLength();
        }
        return lengthAtIntersections.stream().mapToInt(i -> i).min().getAsInt();
    }

    public static void main(String[] args) {
        String[][] inputLines;
        try {
            inputLines = Input.getLinesSplit("day3.txt", ",");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return;
        }
        Wires w = new Wires(inputLines[0], inputLines[1]);
        System.out.println("Closest intersection is " + w.getMinimumIntersectDistance() + " units away.");
        System.out.println("Shortest wire length to intersection is " + w.getShortestWireLengthIntersection() + " units.");
    }
}
