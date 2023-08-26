package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static Map<String, String> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        return keys.stream()
            .collect(Collectors.toMap(key -> key,
                key -> getDescription(data1.get(key), data2.get(key)),
                (v1, v2) -> v1,
                LinkedHashMap::new));
    }

    private static String getDescription(Object value1, Object value2) {
        if (value1 == null && value2 != null) {
            return "added";
        }
        if (value1 != null && value2 == null) {
            return "deleted";
        }
        if (!value1.equals(value2)) {
            return "changed";
        }
        return "unchanged";
    }
}
//END
