package day8;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Layer {
    public final int WIDTH;
    public final int HEIGHT;
    private int[] pixels;

    public Layer(int w, int h, int[] data) {
        this.WIDTH = w;
        this.HEIGHT = h;
        this.pixels = data;
    }

    public int[] getPixels() {
        return pixels;
    }

    public int countDigits(int digit) {
        return Arrays.stream(pixels)
                .map(d -> d == digit ? 1 : 0)
                .sum();
    }

    public void draw(BufferedImage img) {
        int BLACK = Color.black.getRGB();
        int WHITE = Color.white.getRGB();
        int[] data = this.getPixels();
        for (int i = 0; i < WIDTH * HEIGHT; i++) {
            switch (data[i]) {
                case 0:
                    img.setRGB(i % WIDTH, i / WIDTH, BLACK);
                    break;
                case 1:
                    img.setRGB(i % WIDTH, i / WIDTH, WHITE);
                    break;
            }
        }
    }
}
