package exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import java.util.concurrent.CompletableFuture;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

class AppTest {
    private String destPath;

    private static Path getFullPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    @BeforeEach
    void beforeEach() throws Exception {
        destPath = Files.createTempFile("test", "tmp").toString();
    }

    @Test
    void testUnion() throws Exception {
        CompletableFuture<String> result = App.unionFiles(
            "src/test/resources/file1.txt",
            "src/test/resources/file2.txt",
            destPath
        );
        result.get();

        String actual = Files.readString(getFullPath(destPath));
        assertThat(actual).contains("Test", "Message");
    }

    @Test
    void testUnionWithNonExistedFile() throws Exception {

        String result = tapSystemOut(() -> {
            App.unionFiles("nonExistingFile", "file", destPath).get();
        });

        assertThat(result.trim()).contains("NoSuchFileException");
    }

    // BEGIN
    @Test
    void testGetDirectorySizeWhenNonEmptyDirectory() throws Exception {
        var dir = Files.createTempDirectory(null);
        var file1 = Files.createTempFile(dir, null, null);
        var file2 = Files.createTempFile(dir, null, null);
        var string1 = "123";
        Files.writeString(file1, string1);
        var string2 = "4567";
        Files.writeString(file2, string2);


        CompletableFuture<Long> feature = App.getDirectorySize(dir.toString());
        var actual = feature.get();
        long expected = string1.length() + string2.length();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testGetDirectorySizeWhenEmptyDirectory() throws Exception {
        var dir = Files.createTempDirectory(null);


        CompletableFuture<Long> feature = App.getDirectorySize(dir.toString());
        var actual = feature.get();
        long expected = 0L;

        assertThat(actual).isEqualTo(expected);
    }
    // END
}
