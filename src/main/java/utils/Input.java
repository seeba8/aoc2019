package utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
}
