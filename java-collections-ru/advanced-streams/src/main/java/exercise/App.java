package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

// BEGIN
public class App {

    private static final String PREFIX = "X_FORWARDED_";

    public static String getForwardedVariables(String data) {
        return Arrays.stream(data.split("\n"))
            .filter(s -> s.contains(PREFIX))
            .flatMap(App::splitLine)
            .collect(Collectors.toMap(s -> s[0], s -> s[1], (v1, v2) -> v1, LinkedHashMap::new))
            .entrySet()
            .stream()
            .map(e -> e.getKey() + "=" + e.getValue())
            .collect(Collectors.joining(","));
    }

    public static Stream<String[]> splitLine(String line) {
        return Arrays.stream(line.split("[,\"]"))
            .filter(s -> s.contains(PREFIX))
            .map(s -> s.substring(s.indexOf(PREFIX) + PREFIX.length()))
            .map(s -> s.split("="));
    }
}
//END
