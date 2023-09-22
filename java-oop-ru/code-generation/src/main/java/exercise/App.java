package exercise;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import lombok.SneakyThrows;

// BEGIN
public class App {

    @SneakyThrows
    public static void save(Path path, Car car) {
        Files.writeString(path, car.serialize(), StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
    }

    @SneakyThrows
    public static Car extract(Path path) {
        return Car.unserialize(Files.readString(path));
    }
}
// END
