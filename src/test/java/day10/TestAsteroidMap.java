package day10;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAsteroidMap {

    AsteroidMap map1 = new AsteroidMap((".#..#\n" +
            ".....\n" +
            "#####\n" +
            "....#\n" +
            "...##").split("\n"));
    AsteroidMap map2 = new AsteroidMap(("......#.#.\n" +
            "#..#.#....\n" +
            "..#######.\n" +
            ".#.#.###..\n" +
            ".#..#.....\n" +
            "..#....#.#\n" +
            "#..#....#.\n" +
            ".##.#..###\n" +
            "##...#..#.\n" +
            ".#....####").split("\n"));
    AsteroidMap map3 = new AsteroidMap(("#.#...#.#.\n" +
            ".###....#.\n" +
            ".#....#...\n" +
            "##.#.#.#.#\n" +
            "....#.#.#.\n" +
            ".##..###.#\n" +
            "..#...##..\n" +
            "..##....##\n" +
            "......#...\n" +
            ".####.###.").split("\n"));
    AsteroidMap map4 = new AsteroidMap((".#..#..###\n" +
            "####.###.#\n" +
            "....###.#.\n" +
            "..###.##.#\n" +
            "##.##.#.#.\n" +
            "....###..#\n" +
            "..#.#..#.#\n" +
            "#..#.#.###\n" +
            ".##...##.#\n" +
            ".....#.#..").split("\n"));
    AsteroidMap map5 = new AsteroidMap((".#..##.###...#######\n" +
            "##.############..##.\n" +
            ".#.######.########.#\n" +
            ".###.#######.####.#.\n" +
            "#####.##.#.##.###.##\n" +
            "..#####..#.#########\n" +
            "####################\n" +
            "#.####....###.#.#.##\n" +
            "##.#################\n" +
            "#####.##.###..####..\n" +
            "..######..##.#######\n" +
            "####.##.####...##..#\n" +
            ".#####..#.######.###\n" +
            "##...#.##########...\n" +
            "#.##########.#######\n" +
            ".####.#.###.###.#.##\n" +
            "....##.##.###..#####\n" +
            ".#.#.###########.###\n" +
            "#.#.#.#####.####.###\n" +
            "###.##.####.##.#..##\n").split("\n"));

    @Test
    public void testVisibility() {
        assertEquals(8, map1.getNumberOfVisibleAsteroids(new Asteroid(3, 4)));
        assertEquals(7, map1.getNumberOfVisibleAsteroids(new Asteroid(1, 0)));
        assertEquals(7, map1.getNumberOfVisibleAsteroids(new Asteroid(4, 0)));
        assertEquals(5, map1.getNumberOfVisibleAsteroids(new Asteroid(4, 2)));

        assertEquals(33, map2.getNumberOfVisibleAsteroids(new Asteroid(5, 8)));

        assertEquals(35, map3.getNumberOfVisibleAsteroids(new Asteroid(1, 2)));

        assertEquals(41, map4.getNumberOfVisibleAsteroids(new Asteroid(6, 3)));

        assertEquals(210, map5.getNumberOfVisibleAsteroids(new Asteroid(11, 13)));
    }

    @Test
    public void testGetAngles() {
        Asteroid x = new Asteroid(0, 0);
        assertEquals(0, x.getAngle(new Asteroid(0, -1)), 0.001);
        assertEquals(0.25 * Math.PI, x.getAngle(new Asteroid(1, -1)), 0.001);
        assertEquals(0.5 * Math.PI, x.getAngle(new Asteroid(1, 0)), 0.001);
        assertEquals(0.75 * Math.PI, x.getAngle(new Asteroid(1, 1)), 0.001);
        assertEquals(Math.PI, x.getAngle(new Asteroid(0, 1)), 0.001);
        assertEquals(1.25 * Math.PI, x.getAngle(new Asteroid(-1, 1)), 0.001);
        assertEquals(1.5 * Math.PI, x.getAngle(new Asteroid(-1, 0)), 0.001);
        assertEquals(1.75 * Math.PI, x.getAngle(new Asteroid(-1, -1)), 0.001);
    }

    @Test
    public void testGetAsteroidAngles() {
        AsteroidMap map = new AsteroidMap((".#....#####...#..\n" +
                "##...##.#####..##\n" +
                "##...#...#.#####.\n" +
                "..#.....X...###..\n" +
                "..#.#.....#....##").split("\n"));
        Map<Double, Set<Asteroid>> asteroidAngles = map.getAsteroidAngles(new Asteroid(8, 3));
    }

    @Test
    public void testVaporisationOrder() {
        List<Asteroid> asteroidList = map5.vaporise(new Asteroid(11, 13));
        assertEquals(0, asteroidList.indexOf(new Asteroid(11, 12)));
        assertEquals(1, asteroidList.indexOf(new Asteroid(12, 1)));
        assertEquals(2, asteroidList.indexOf(new Asteroid(12, 2)));
        assertEquals(9, asteroidList.indexOf(new Asteroid(12, 8)));
        assertEquals(19, asteroidList.indexOf(new Asteroid(16, 0)));
        assertEquals(49, asteroidList.indexOf(new Asteroid(16, 9)));
        assertEquals(99, asteroidList.indexOf(new Asteroid(10, 16)));
        assertEquals(198, asteroidList.indexOf(new Asteroid(9, 6)));
        assertEquals(199, asteroidList.indexOf(new Asteroid(8, 2)));
        assertEquals(199, asteroidList.indexOf(new Asteroid(8, 2)));
    }

    @Test
    public void testGetBestAsteroid() {
        assertEquals(new Asteroid(3, 4), map1.getBestAsteroid());
        assertEquals(new Asteroid(5, 8), map2.getBestAsteroid());
        assertEquals(new Asteroid(1, 2), map3.getBestAsteroid());
        assertEquals(new Asteroid(6, 3), map4.getBestAsteroid());
        assertEquals(new Asteroid(11, 13), map5.getBestAsteroid());
    }
}

