package exercise;

import java.util.Map;
import java.util.logging.Logger;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        var minThread = new MinThread(numbers);
        var maxThread = new MaxThread(numbers);

        minThread.start();
        maxThread.start();

        try {
            minThread.join();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        try {
            maxThread.join();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        return Map.of(
            "min", minThread.getMinimumNumber(),
            "max", maxThread.getMaximumNumber()
        );
    }
    // END
}
