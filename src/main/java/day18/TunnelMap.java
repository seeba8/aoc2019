package day18;

import utils.Point;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TunnelMap {
    private static final char WALL = '#';
    private static final char EMPTY = '.';
    private static final char START = '@';
    private String[] map;
    private int[] validPoints;
    private int mapWidth;
    private int mapHeight;
    private int start;
    private Map<Integer, Character> keys = new HashMap<>();
    private Map<Integer, Character> doors = new HashMap<>();

    public TunnelMap(String[] map) {
        this.map = map;
        mapWidth = map[0].length();
        mapHeight = map.length;
        List<Integer> pointsList = new ArrayList<>();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < mapWidth; x++) {
                char symbol = getSymbolAt(x, y);
                if (symbol == WALL) continue;
                pointsList.add(y * mapWidth + x);
                if (symbol == EMPTY) continue;
                if (symbol == START) {
                    start = y * mapWidth + x;
                    continue;
                }
                if (Character.isLowerCase(symbol)) {
                    keys.put(y * mapWidth + x, symbol);
                } else {
                    doors.put(y * mapWidth + x, symbol);
                }
            }
        }
        validPoints = pointsList.stream().mapToInt(Integer::intValue).toArray();
    }


    char getSymbolAt(int x, int y) {
        if (x < 0) return WALL;
        if (x >= map[0].length()) return WALL;
        if (y < 0) return WALL;
        if (y >= map.length) return WALL;
        return map[y].charAt(x);
    }

    char getSymbolAt(int val) {
        return getSymbolAt(val % mapWidth, val / mapWidth);
    }

    char getSymbolAt(Point<Integer> position) {
        return getSymbolAt(position.x, position.y);
    }

    int getPositionOfKey(char key) {
        for (Map.Entry<Integer, Character> entry : keys.entrySet()) {
            if (entry.getValue() == key) return entry.getKey();
        }
        throw new IllegalStateException("Key not found: " + key);
    }

    Point<Integer> getPoint(int val) {
        return new Point<Integer>(val % mapWidth, val / mapWidth);
    }

    Map<Character, Integer> getPathLengths(int start, int[] givenKeys) {
        IntStream openableDoors = Arrays.stream(givenKeys).map(Character::toUpperCase);
        if (getSymbolAt(start) == WALL) return null;
        Map<Character, Integer> distances = new HashMap<>();
        int[] distance = new int[mapHeight * mapWidth];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> distance[o]));
        queue.addAll(Arrays.stream(validPoints.clone()).boxed().collect(Collectors.toList()));
        while (!queue.isEmpty()) {
            int u = queue.poll();
            if (keys.containsKey(u)) {
                distances.put(keys.get(u), distance[u]);
            }
            if (distances.size() == keys.size()) return distances;
            Point<Integer> uPoint = getPoint(u);
            List<Integer> neighbours = new ArrayList<>();
            if (uPoint.x != 0) neighbours.add(u - 1);
            if (uPoint.x != mapWidth - 1) neighbours.add(u + 1);
            if (uPoint.y != 0) neighbours.add(u - mapWidth);
            if (uPoint.y != mapHeight - 1) neighbours.add(u + mapWidth);
            for (int v : neighbours) {
                if (!queue.contains(v)) continue;
                int symbol = getSymbolAt(v);
                if (Character.isUpperCase(symbol) && openableDoors.noneMatch(x -> symbol == x)) continue;
                int newDistance = distance[u] + 1;
                if (newDistance < distance[v]) {
                    distance[v] = newDistance;
                    queue.remove(v);
                    queue.add(v);
                }
            }

        }
        return distances;
    }

    protected int getStart() {
        return start;
    }
}
