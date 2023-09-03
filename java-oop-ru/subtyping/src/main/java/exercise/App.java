package exercise;

// BEGIN
public class App {

    public static void swapKeyValue(KeyValueStorage data) {
        var map = data.toMap();

        map.forEach((key, value) -> data.unset(key));
        map.forEach((key, value) -> data.set(value, key));
    }
}
// END
