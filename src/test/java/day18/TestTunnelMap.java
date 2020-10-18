package day18;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTunnelMap {
    static String example1 =
            "#########\n" +
                    "#b.A.@.a#\n" +
                    "#########";

    @Test
    void testDistances() {
        TunnelMap map = new TunnelMap(example1.split("\n"));
        final Map<Character, Integer> pathLengths = map.getPathLengths(map.getStart(), new int[0]);
        //System.out.println(pathLengths.toString());
        assertEquals(2, pathLengths.get('a'));
        assertEquals(Integer.MAX_VALUE, pathLengths.get('b'));
    }

    @Test
    void testGetPositionOfKey() {
        TunnelMap map = new TunnelMap(example1.split("\n"));
        assertEquals(16, map.getPositionOfKey('a'));
    }

    @Test
    void testDistancesWithKey() {
        TunnelMap map = new TunnelMap(example1.split("\n"));
        int aPosition = map.getPositionOfKey('a');
        final Map<Character, Integer> pathLengths = map.getPathLengths(aPosition, new int[]{'a'});
        assertEquals(6, pathLengths.get('b'));
    }
}
