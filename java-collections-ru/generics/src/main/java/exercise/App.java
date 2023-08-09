package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
public class App {

    public static <T extends Map<String, String>> List<T> findWhere(List<T> books, T where) {
        var result = new ArrayList<T>();

        if (where.isEmpty()) {
            return result;
        }

        for (var book: books) {
            if (isMatch(book, where)) {
                result.add(book);
            }
        }

        return result;
    }

    private static <T extends Map<String, String>> boolean isMatch(T book, T where) {
        if (book.isEmpty()) {
            return false;
        }

        for (var attribute: where.entrySet()) {
            var id = attribute.getKey();
            var value = attribute.getValue();
            if (!value.equals(book.get(id))) {
                return false;
            }
        }

        return true;
    }
}
//END
