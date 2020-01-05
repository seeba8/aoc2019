package utils;

import java.util.Objects;

public class Point3D<T> {

    public T x;
    public T y;
    public T z;

    public Point3D(T x, T y, T z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
            Point3D<T> other = (Point3D<T>) obj;
            return this.x == other.x && this.y == other.y && this.z == other.z;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("<x=%2s, y=%2s, z=%2s>", x.toString(), y.toString(), z.toString());
    }

    public T getAxis(int axis) {
        switch (axis) {
            case 0:
                return x;
            case 1:
                return y;
            case 2:
                return z;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    public void setAxis(int axis, T value) {
        switch (axis) {
            case 0:
                x = value;
                return;
            case 1:
                y = value;
                return;
            case 2:
                z = value;
                return;
            default:
                throw new IndexOutOfBoundsException();
        }
    }
}
