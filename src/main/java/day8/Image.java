package day8;

import utils.Input;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;

public class Image {
    public final int WIDTH;
    public final int HEIGHT;
    private List<Layer> layers = new ArrayList<>();

    public Image(int w, int h) {
        WIDTH = w;
        HEIGHT = h;
    }

    public static void main(String[] args) {
        int[] data;
        try {
            data = Input.getIntArrayFromSingleLine("day8.txt", "");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        Image img = new Image(25, 6);
        img.setImageData(data);
        System.out.printf("Checksum: %s", img.getChecksum());
        img.render();
    }

    public void setImageData(int[] data) {
        assert (data.length % (WIDTH * HEIGHT) == 0);
        int numLayers = data.length / (WIDTH * HEIGHT);
        for (int i = 0; i < numLayers; i++) {
            layers.add(new Layer(WIDTH, HEIGHT,
                    Arrays.copyOfRange(data, i * WIDTH * HEIGHT, (i + 1) * (WIDTH * HEIGHT))));
        }
    }

    public int getChecksum() {
        Layer fewestZeros = getLayerWithFewest(0);
        return fewestZeros.countDigits(1) * fewestZeros.countDigits(2);
    }

    public Layer getLayer(int i) {
        return layers.get(i);
    }

    private Layer getLayerWithFewest(int i) {
        Layer resultingLayer = null;
        int min = Integer.MAX_VALUE;
        for (Layer l : layers) {
            int numberOfI = l.countDigits(i);
            if (numberOfI < min) {
                resultingLayer = l;
                min = numberOfI;
            }
        }
        return resultingLayer;
    }

    public void render() {
        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        for (int i = layers.size() - 1; i > -1; i--) {
            layers.get(i).draw(img);
        }
        File outfile = new File("day8_out.png");
        try {
            ImageIO.write(img, "png", outfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("The resulting image is at %s\n", outfile.getAbsolutePath());
    }


}
