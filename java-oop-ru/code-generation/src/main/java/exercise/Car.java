package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() throws JsonProcessingException {
        var objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(this);
    }

    public static Car unserialize(String json) throws IOException {
        var objectMapper = new ObjectMapper();

        return objectMapper.readValue(json, Car.class);
    }
    // END
}
