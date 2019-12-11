package utils;

import java.util.Objects;

public class Point<T> {
    public T x;
    public T y;

    public Point(T x, T y) {
        this.x = x;
        this.y = y;
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
