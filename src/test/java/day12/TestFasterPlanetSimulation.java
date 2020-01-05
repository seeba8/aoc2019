package day12;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestFasterPlanetSimulation {

    @Test
    public void testExampleSimulation() {
        FasterPlanetSimulation j = new FasterPlanetSimulation(new String[]{
                "<x=-1, y=0, z=2>", "<x=2, y=-10, z=-7>", "<x=4, y=-8, z=8>", "<x=3, y=5, z=-1>"
        });
        j.simulate();
        assertArrayEquals(new int[]{
                2,3,-1,-1,1,-1,
                3,1,-7,3,-4,3,
                1,-3,-7,1,5,-3,
                2,-1,2,-3,0,1

        }, j.state);
    }

    @Test
    public void testTotalEnergy() {
        FasterPlanetSimulation j = new FasterPlanetSimulation(new String[]{
                "<x=-1, y=0, z=2>", "<x=2, y=-10, z=-7>", "<x=4, y=-8, z=8>", "<x=3, y=5, z=-1>"
        });
        j.simulate(10);
        assertEquals(179, j.getTotalEnergy());

        j = new FasterPlanetSimulation(("<x=-8, y=-10, z=0>\n" +
                "<x=5, y=5, z=10>\n" +
                "<x=2, y=-7, z=3>\n" +
                "<x=9, y=-8, z=-3>").split("\n"));
        j.simulate(100);
        assertEquals(1940, j.getTotalEnergy());
    }

    /*
    @Test
    public void testHowLongALotOfSimulationTakes() {
        FasterPlanetSimulation j = new FasterPlanetSimulation(("<x=-8, y=-10, z=0>\n" +
                "<x=5, y=5, z=10>\n" +
                "<x=2, y=-7, z=3>\n" +
                "<x=9, y=-8, z=-3>").split("\n"));
        j.simulate(4_686_774_924L);
    }
    */
    @Test
    public void testUntilLoop() {
        FasterPlanetSimulation j = new FasterPlanetSimulation(new String[]{
                "<x=-1, y=0, z=2>", "<x=2, y=-10, z=-7>", "<x=4, y=-8, z=8>", "<x=3, y=5, z=-1>"
        });
        assertEquals(2772, j.getNumberOfStepsUntilRepeat());
    }

    //@Test
    public void testManyStepsUntilLoop() {
        FasterPlanetSimulation j = new FasterPlanetSimulation(("<x=-8, y=-10, z=0>\n" +
                "<x=5, y=5, z=10>\n" +
                "<x=2, y=-7, z=3>\n" +
                "<x=9, y=-8, z=-3>").split("\n"));
        j.simulate(4_686_774_924L);
    }

    //@Test
    public void testLongExampleUntilLoop() {
        FasterPlanetSimulation j = new FasterPlanetSimulation(("<x=-8, y=-10, z=0>\n" +
                "<x=5, y=5, z=10>\n" +
                "<x=2, y=-7, z=3>\n" +
                "<x=9, y=-8, z=-3>").split("\n"));
        assertEquals(4_686_774_924L, j.getNumberOfStepsUntilRepeat());
    }

}
