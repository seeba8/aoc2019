package day3;

import utils.Point;

public class Wire {
    public int x1, x2, y1, y2;
    public String direction;

    public Wire(int x1, int x2, int y1, int y2) {
        this.x1 = x1 <= x2 ? x1 : x2; // x1 is smaller than x2
        this.x2 = x2 >= x1 ? x2 : x1;
        this.y1 = y1 <= y2 ? y1 : y2; // y1 is smaller than y2
        this.y2 = y2 >= y1 ? y2 : y1;
    }

    public Wire(int x1, int x2, int y1, int y2, String direction) {
        this(x1, x2, y1, y2);
        this.direction = direction;
    }

    public boolean isVertical() {
        return this.x1 == this.x2;
    }

    public int getLength() {
        if (isVertical()) {
            return Math.abs(this.y2 - this.y1);
        }
        return Math.abs(this.x2 - this.x1);
    }

    public boolean intersects(Wire other) {
        if (this.x1 > other.x2) return false; // other <-----> this
        if (this.x2 < other.x1) return false; // this <------> other
        if (this.y1 > other.y2) return false; // this is above other
        if (this.y2 < other.y1) return false; // this is below other

        if (this.isVertical() != other.isVertical())
            return true; // they don't go the same direction, so they must intersect

        if (this.isVertical() && this.x1 == other.x1) return true; // overlap vertically
        return !this.isVertical() && this.y1 == other.y1; // whether they overlap horizontally
    }

    public int intersectDistance(Wire other) {
        Point<Integer> intersectPoint = getIntersectPoint(other);
        return Math.abs(intersectPoint.x) + Math.abs(intersectPoint.y);
    }

    public Point<Integer> getIntersectPoint(Wire other) {
        if (!this.intersects(other)) {
            throw new IllegalStateException("They must intersect for you to calculate the intersection point");
        }
        if (this.isVertical() != other.isVertical()) { // one is vertical, the other horizontal
            if (this.isVertical()) {
                return new Point<>(this.x1, other.y1);
            } else {
                return new Point<>(other.x1, this.y1);
            }

        } else { // they go the same direction
            int this1, other1, axis;
            if (this.isVertical()) {
                if (this.y1 < other.y1) {
                    return new Point<>(this.x1, other.y1);
                }
                return new Point<>(this.x1, this.y1);
            } else {
                if (this.x1 < other.x1) {
                    return new Point<>(other.x1, this.y1);
                }
                return new Point<>(this.x1, this.y1);

            }
        }
    }

    public int getDistanceToPoint(Point<Integer> p, boolean inDirection) {
        if (!inDirection) throw new IllegalStateException("Not implemented");
        switch (direction) {
            case "U":
                return Math.abs(p.y - y1);
            case "R":
                return Math.abs(p.x - x1);
            case "D":
                return Math.abs(p.y - y2);
            case "L":
                return Math.abs(p.x - x2);
            default:
                throw new IllegalStateException("unknown direction");
        }
    }
}

