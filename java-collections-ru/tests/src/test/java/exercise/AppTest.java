package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        var sourceList = Arrays.asList(0, 1, 2, 3, 4);
        var size = 3;
        var actual = App.take(sourceList, size);
        var expected = sourceList.subList(0, size);
        assertThat(actual).hasSize(size)
            .containsExactlyElementsOf(expected);
        // END
    }
}
