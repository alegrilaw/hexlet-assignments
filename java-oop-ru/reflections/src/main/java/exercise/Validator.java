package exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {

    private static final String LENGTH_ERROR = "length less than %s";
    private static final String NULL_ERROR = "can not be null";

    public static List<String> validate(Address address) {
        List<String> result = new ArrayList<>();

        for (var field : address.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(address) == null) {
                        result.add(field.getName());
                    }
                } catch (Exception ignored) {}
            }
        }

        return result;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> result = new HashMap<>();

        for (var field : address.getClass().getDeclaredFields()) {
            List<String> errors = new ArrayList<>();

            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(address) == null) {
                        errors.add(NULL_ERROR);
                    }
                } catch (Exception ignored) {}
            }

            if (field.isAnnotationPresent(MinLength.class)) {
                var minLength = field.getDeclaredAnnotationsByType(MinLength.class)[0].minLength();
                try {
                    String string = (String) field.get(address);
                    if (string != null && string.length() < minLength) {
                        errors.add(String.format(LENGTH_ERROR, minLength));
                    }
                } catch (Exception ignored) {}
            }

            if (!errors.isEmpty()) {
                result.put(field.getName(), errors);
            }
        }

        return result;
    }
}
// END
