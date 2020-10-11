package day12;

import org.junit.jupiter.api.Test;
import utils.Point3D;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMoon {

    @Test
    public void testMoonCreation() {
        Point3D<Integer> zero = new Point3D<>(0, 0, 0);
        Moon a = new Moon("<x=-1, y=0, z=2>");
        assertEquals(new Point3D<>(-1, 0, 2), a.position);
        assertEquals(zero, a.velocity);

        a = new Moon("<x=2, y=-10, z=-7>");
        assertEquals(new Point3D<>(2, -10, -7), a.position);
    }

    @Test
    public void testTotalEnergy() {
        Moon m = new Moon("<x=2, y=0, z=4>");
        m.velocity = new Point3D<>(1,-1,-1);
        assertEquals(18,m.getTotalEnergy());
    }
}
