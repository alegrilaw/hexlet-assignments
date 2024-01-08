package exercise;

import java.util.NoSuchElementException;

class SafetyList {

    private static final int INITIAL_SIZE = 16;

    // BEGIN
    private int[] numbers = new int[INITIAL_SIZE];
    private int size = 0;

    synchronized void add(int value) {
        if (size == numbers.length) {
            var numbers_new = new int[2 * numbers.length];
            System.arraycopy(numbers, 0, numbers_new, 0, numbers.length);
            numbers = numbers_new;
        }

        numbers[size++] = value;
    }

    int get(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }

        return numbers[index];
    }

    int getSize() {
        return size;
    }
// END
}
