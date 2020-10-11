package day6;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestOrbits {
    OrbitMap map;

    @Test
    public void testNumOrbits() {
        map = new OrbitMap(new String[]{"COM)B",
                "B)C",
                "C)D",
                "D)E",
                "E)F",
                "B)G",
                "G)H",
                "D)I",
                "E)J",
                "J)K",
                "K)L"});
        assertEquals(3, map.getPlanet("D").getNumberOfOrbits());
        assertEquals(7, map.getPlanet("L").getNumberOfOrbits());
        assertEquals(0, map.getPlanet("COM").getNumberOfOrbits());
    }

    @Test
    public void testTotalOrbits() {
        map = new OrbitMap(new String[]{"COM)B",
                "B)C",
                "C)D",
                "D)E",
                "E)F",
                "B)G",
                "G)H",
                "D)I",
                "E)J",
                "J)K",
                "K)L"});
        assertEquals(42, map.getTotalNumberOfOrbits());
    }

    @Test
    public void testNumberOfTransfers() {
        map = new OrbitMap(new String[]{"COM)B",
                "B)C",
                "C)D",
                "D)E",
                "E)F",
                "B)G",
                "G)H",
                "D)I",
                "E)J",
                "J)K",
                "K)L",
                "K)YOU",
                "I)SAN"});
        assertEquals(4, map.getPlanet("YOU").getNumberOfTransfersTo(map.getPlanet("SAN")));
    }
}
