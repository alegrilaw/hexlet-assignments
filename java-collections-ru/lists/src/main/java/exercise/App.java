package exercise;

import java.util.ArrayList;
import java.util.List;

// BEGIN
public class App {

    public static boolean scrabble(String stringOfSymbols, String word) {
        List<Character> validSymbols = new ArrayList<>();
        for (Character symbol : stringOfSymbols.toLowerCase().toCharArray()) {
            validSymbols.add(symbol);
        }

        for (Character symbol : word.toLowerCase().toCharArray()) {
            if (validSymbols.contains(symbol)) {
                validSymbols.remove(symbol);
            } else {
                return false;
            }
        }

        return true;
    }
}
//END
