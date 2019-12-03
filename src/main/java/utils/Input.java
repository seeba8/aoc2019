package utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Input {
    public static int[] getIntegerArray(String filename) throws URISyntaxException, IOException {
        return getIntegerStream(filename).toArray();
    }

    public static IntStream getIntegerStream(String filename) throws IOException, URISyntaxException {
        Path p = Paths.get(Input.class.getClassLoader().getResource(filename).toURI());
        return Files.readAllLines(p)
                .stream()
                .mapToInt(Integer::parseInt);
    }

    public static String[] getStringArrayFromSingleLine(String filename, String separator) throws IOException, URISyntaxException {
        Path p = Paths.get(Input.class.getClassLoader().getResource(filename).toURI());
        return Files.readAllLines(p)
                .get(0)
                .split(",");
    }

    public static int[] getIntArrayFromSingleLine(String filename, String separator) throws IOException, URISyntaxException {
        return Arrays.stream(getStringArrayFromSingleLine(filename, separator))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static String[][] getLinesSplit(String filename, String separator) throws URISyntaxException, IOException {
        Path p = Paths.get(Input.class.getClassLoader().getResource(filename).toURI());
        return Files.readAllLines(p)
                .stream()
                .map(line -> line.split(separator))
                .toArray(String[][]::new);
    }
}
