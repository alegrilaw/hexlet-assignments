package exercise;

import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
public class App {

    public static String[][] enlargeArrayImage(String[][] image) {
        if (image.length == 0) {
            return new String[][]{};
        }

        final String[][] result = new String[2 * image.length][];
        return Arrays.stream(image)
            .flatMap(row -> Stream.of(
                Arrays.stream(row)
                    .flatMap(s -> Stream.of(s, s))
                    .toArray(String[]::new),
                Arrays.stream(row)
                    .flatMap(s -> Stream.of(s, s))
                    .toArray(String[]::new))
            )
            .toArray(String[][]::new);
    }
}
// END
