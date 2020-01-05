package day12;

import org.junit.Test;
import utils.Point3D;

import static org.junit.Assert.assertEquals;

public class TestJupiter {

    @Test
    public void testExampleSimulation() {
        Jupiter j = new Jupiter(new String[]{
                "<x=-1, y=0, z=2>", "<x=2, y=-10, z=-7>", "<x=4, y=-8, z=8>", "<x=3, y=5, z=-1>"
        });
        j.simulate();
        assertEquals(new Point3D<Integer>(2, -1, 1), j.getMoon(0).position);
        assertEquals(new Point3D<Integer>(3, -1, -1), j.getMoon(0).velocity);
        assertEquals(new Point3D<Integer>(3, -7, -4), j.getMoon(1).position);
        assertEquals(new Point3D<Integer>(1, 3, 3), j.getMoon(1).velocity);
        assertEquals(new Point3D<Integer>(1, -7, 5), j.getMoon(2).position);
        assertEquals(new Point3D<Integer>(-3, 1, -3), j.getMoon(2).velocity);
        assertEquals(new Point3D<Integer>(2, 2, 0), j.getMoon(3).position);
        assertEquals(new Point3D<Integer>(-1, -3, 1), j.getMoon(3).velocity);
    }

    @Test
    public void testTotalEnergy() {
        Jupiter j = new Jupiter(new String[]{
                "<x=-1, y=0, z=2>", "<x=2, y=-10, z=-7>", "<x=4, y=-8, z=8>", "<x=3, y=5, z=-1>"
        });
        for (int i = 0; i < 10; i++) {
            j.simulate();
        }
        assertEquals(179, j.getTotalEnergy());

        j = new Jupiter(("<x=-8, y=-10, z=0>\n" +
                "<x=5, y=5, z=10>\n" +
                "<x=2, y=-7, z=3>\n" +
                "<x=9, y=-8, z=-3>").split("\n"));
        j.simulate(100);
        assertEquals(1940, j.getTotalEnergy());
    }
/*
    @Test
    public void testHowLongALotOfSimulationTakes() {
        Jupiter j = new Jupiter(("<x=-8, y=-10, z=0>\n" +
                "<x=5, y=5, z=10>\n" +
                "<x=2, y=-7, z=3>\n" +
                "<x=9, y=-8, z=-3>").split("\n"));
        j.simulate(4_686_774_924L);
    }
*/
}
