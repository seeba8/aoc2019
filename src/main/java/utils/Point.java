package utils;

import java.util.Objects;

public class Point<T> implements Cloneable {
    public T x;
    public T y;

    public Point(T x, T y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Point<T> clone() {
        Point<T> clone;
        try {
            clone = (Point<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone didn't work...");
        }
        clone.x = x;
        clone.y = y;
        return clone;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        try {
            @SuppressWarnings("unchecked")
            Point<T> other = (Point<T>) obj;
            return this.x == other.x && this.y == other.y;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", x.toString(), y.toString());
    }
}
