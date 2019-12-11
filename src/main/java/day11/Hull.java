package day11;

import utils.Input;
import utils.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Hull {
    private Map<Point<Integer>, Colour> paintedPanels = new HashMap<>();

    public static void main(String[] args) {
        long[] input;
        try {
            input = Input.getLongArrayFromSingleLine("day11.txt", ",");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        PaintingRobot robby = new PaintingRobot(input.clone());
        robby.run();
        System.out.printf("%d Panels were painted at least once.\n", robby.getNumberOfPaintedPanels());

        robby = new PaintingRobot(input.clone());
        robby.getHull().paint(new Point<Integer>(0, 0), Colour.WHITE);
        robby.run();
        robby.getHull().render();
    }

    public void paint(Point<Integer> position, Colour colour) {
        paintedPanels.put(new Point<>(position.x, position.y), colour);
    }

    public Colour getColour(Point<Integer> position) {
        return paintedPanels.getOrDefault(position, Colour.BLACK);
    }

    public int getNumberOfPaintedPanels() {
        return paintedPanels.size();
    }

    public void render() {
        int minX = paintedPanels.keySet().stream().mapToInt(p -> p.x).min().orElseThrow(IllegalStateException::new);
        int maxX = paintedPanels.keySet().stream().mapToInt(p -> p.x).max().orElseThrow(IllegalStateException::new);
        int minY = paintedPanels.keySet().stream().mapToInt(p -> p.y).min().orElseThrow(IllegalStateException::new);
        int maxY = paintedPanels.keySet().stream().mapToInt(p -> p.y).max().orElseThrow(IllegalStateException::new);
        BufferedImage img = new BufferedImage(maxX - minX + 1, maxY - minY + 1, BufferedImage.TYPE_INT_RGB);
        for (Map.Entry<Point<Integer>, Colour> next : paintedPanels.entrySet()) {
            if (next.getValue() == Colour.BLACK) {
                img.setRGB(next.getKey().x - minX, next.getKey().y - minY, Color.BLACK.getRGB());
            } else if (next.getValue() == Colour.WHITE) {
                img.setRGB(next.getKey().x - minX, next.getKey().y - minY, Color.WHITE.getRGB());
            }
        }
        File outfile = new File("day11_out.png");
        try {
            ImageIO.write(img, "png", outfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("The resulting image is at %s\n", outfile.getAbsolutePath());
    }

    public enum Colour {
        BLACK,
        WHITE;
    }
}
