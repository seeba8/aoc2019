package day13;

import day5.AdvancedIntcodeComputer;
import utils.Input;
import utils.Point;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class ArcadeComputer extends AdvancedIntcodeComputer {
    Map<Point<Integer>, Tile> screen = new HashMap<>();
    private int nextOutputPosition = 0;
    private int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
    private int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
    private int bufx = 0, bufy = 0;
    private Point<Integer> ball;
    private Point<Integer> paddle;

    ArcadeComputer(long[] programme) {
        super(programme, 0);
    }

    @Override
    protected long getInput() {
        return 0;
    }

    @Override
    public void output(long value) {
        if(nextOutputPosition == 0) {
            bufx = (int)value;
            minX = Math.min(minX, bufx);
            maxX = Math.max(maxX, bufx);
        } else if(nextOutputPosition == 1) {
            bufy = (int)value;
            minY = Math.min(minY, bufy);
            maxY = Math.max(maxY, bufy);
        } else {
            Tile t = Tile.getTile((int)value);
            Point<Integer> p = new Point<>(bufx, bufy);
            screen.put(p, t);
            if(t == Tile.BALL) {
                ball = p;
            } else if (t == Tile.PADDLE) {
                paddle = p;
            }
        }

        nextOutputPosition = (nextOutputPosition + 1) % 3;
    }

    public void printScreen() {
        StringBuilder s = new StringBuilder((maxX - minX + 1) * (maxY - minY + 1));
        // fill with spaces
        for (int i = 0; i < s.capacity(); i++) {
            s.insert(i, " ");
        }
        for(Map.Entry<Point<Integer>, Tile> entry : screen.entrySet()) {
            s.setCharAt((entry.getKey().y - minY) * (maxX - minX) + (entry.getKey().x - minX),
                    entry.getValue().getChar());
        }
        // go from back to front in order to have the index easier
        for(int i = maxY - minY ; i > 0; i--) {
            s.insert(i * (maxX - minX) , "\n");
        }
        System.out.println(s.toString());
    }

    public long countTiles(Tile type) {
        return screen.values().stream().filter(tile -> tile == type).count();
    }

    public static void main(String[] args) {
        long[] input;
        try {
            input = Input.getLongArrayFromSingleLine("day13.txt", ",");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        ArcadeComputer ac = new ArcadeComputer(input);
        ac.run();
        ac.printScreen();
        System.out.printf("Blocks: %s\n", ac.countTiles(Tile.BLOCK));
    }
}
