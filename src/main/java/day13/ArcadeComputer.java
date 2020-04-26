package day13;

import day5.AdvancedIntcodeComputer;
import utils.Input;
import utils.Point;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ArcadeComputer extends AdvancedIntcodeComputer {
    Map<Point<Integer>, Tile> screen = new HashMap<>();
    private int nextOutputPosition = 0;
    private int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
    private int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
    private int bufx = 0, bufy = 0;
    private int score = 0;
    private Point<Integer> ball;
    private Point<Integer> paddle;
    private static final boolean MANUAL = false;

    ArcadeComputer(long[] programme) {
        super(programme, 0);
    }

    @Override
    protected long getInput() {
        if(MANUAL) {
            printScreen();
            System.out.println("-1 <-- 0 --> 1");
            Scanner sc = new Scanner(System.in);
            return sc.nextLong();
        }
        return ball.x.compareTo(paddle.x); // -1 if smaller, +1 if larger, 0 if equal
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
            Point<Integer> p = new Point<>(bufx, bufy);
            if(p.equals(new Point<Integer>(-1,0))) { // scoreboard
                score = (int)value;
            } else {
                Tile t = Tile.getTile((int)value);
                screen.put(p, t);
                if(t == Tile.BALL) {
                    ball = p;
                } else if (t == Tile.PADDLE) {
                    paddle = p;
                }
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

        ArcadeComputer ac = new ArcadeComputer(input.clone());
        ac.run();
        ac.printScreen();
        System.out.printf("Blocks: %s\n", ac.countTiles(Tile.BLOCK));

        System.out.println("PART 2");
        input[0] = 2;
        ac = new ArcadeComputer(input);
        ac.run();
        System.out.printf("You got %s points!\n", ac.score);
    }
}
