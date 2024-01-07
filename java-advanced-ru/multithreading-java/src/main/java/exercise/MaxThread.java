package exercise;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.logging.Level;
import java.util.logging.Logger;

// BEGIN
public class MaxThread extends Thread {

    private static final Logger LOGGER = Logger.getLogger("MaxThreadLogger");
    private final int[] numbers;
    private OptionalInt maximumNumber = OptionalInt.empty();

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        var threadName = Thread.currentThread().getName();

        LOGGER.log(Level.INFO, "Thread {0} started", threadName);
        maximumNumber = Arrays.stream(numbers).max();
        LOGGER.log(Level.INFO, "Thread {0} finished", threadName);
    }

    public int getMaximumNumber() {
        return maximumNumber.getAsInt();
    }
}
// END
