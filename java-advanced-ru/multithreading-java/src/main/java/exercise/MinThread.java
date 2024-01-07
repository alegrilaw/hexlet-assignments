package exercise;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.logging.Level;
import java.util.logging.Logger;

// BEGIN
public class MinThread extends Thread {

    private static final Logger LOGGER = Logger.getLogger("MinThreadLogger");
    private final int[] numbers;
    private OptionalInt minimumNumber = OptionalInt.empty();

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        var threadName = Thread.currentThread().getName();

        LOGGER.log(Level.INFO, "Thread {0} started", threadName);
        minimumNumber = Arrays.stream(numbers).min();
        LOGGER.log(Level.INFO, "Thread {0} finished", threadName);
    }

    public int getMinimumNumber() {
        return minimumNumber.getAsInt();
    }
}
// END
