package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
public class AppTest {

    @Test
    void shouldEnlargeNonEmptyArray() {
        String[][] source = {
            {"*", "*", "*", "*"},
            {"*", " ", " ", "*"},
            {"*", " ", " ", "*"},
            {"*", "*", "*", "*"}
        };
        String[][] expected = {
            {"*", "*", "*", "*", "*", "*", "*", "*"},
            {"*", "*", "*", "*", "*", "*", "*", "*"},
            {"*", "*", " ", " ", " ", " ", "*", "*"},
            {"*", "*", " ", " ", " ", " ", "*", "*"},
            {"*", "*", " ", " ", " ", " ", "*", "*"},
            {"*", "*", " ", " ", " ", " ", "*", "*"},
            {"*", "*", "*", "*", "*", "*", "*", "*"},
            {"*", "*", "*", "*", "*", "*", "*", "*"}
        };

        String[][] actual = App.enlargeArrayImage(source);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldNotEnlargeEmptyArray() {
        String[][] source = {};
        String[][] expected = {};

        String[][] actual = App.enlargeArrayImage(source);

        assertThat(actual).isEqualTo(expected);
    }
}
// END
