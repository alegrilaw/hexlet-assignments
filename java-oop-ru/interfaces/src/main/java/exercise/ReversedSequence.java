package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {

    private String reversedString;

    public ReversedSequence(String string) {
        reversedString = new StringBuilder(string).reverse().toString();
    }

    @Override
    public int length() {
        return reversedString.length();
    }

    @Override
    public char charAt(int index) {
        return reversedString.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return reversedString.subSequence(start, end);
    }

    @Override
    public String toString() {
        return reversedString;
    }
}
// END
