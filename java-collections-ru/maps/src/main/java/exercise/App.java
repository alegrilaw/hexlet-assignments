package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {

    public static Map<String, Integer> getWordCount(String sentence) {
        var result = new HashMap<String, Integer>();

        for (var word: sentence.split(" ")) {
            if (!word.isEmpty()) {
                var counter = result.get(word);
                counter = counter == null ? 1 : counter + 1;
                result.put(word, counter);
            }
        }

        return result;
    }

    public static String toString(Map<String, Integer> words) {
        var builder = new StringBuilder("{");

        for (var word: words.entrySet()) {
            builder.append("\n")
                .append("  ")
                .append(word.getKey())
                .append(": ")
                .append(word.getValue());
        }

        if (!words.isEmpty()) {
            builder.append("\n");
        }
        builder.append("}");
        return builder.toString();
    }
}
//END
