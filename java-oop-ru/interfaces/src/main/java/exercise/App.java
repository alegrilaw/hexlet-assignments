package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static List<String> buildApartmentsList(List<Home> apartments, int size) {
        return apartments.stream()
            .sorted(Home::compareTo)
            .limit(size)
            .map(Home::toString)
            .collect(Collectors.toList());
    }
}
// END
