package exercise;

import java.io.IOException;
import java.nio.file.OpenOption;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.function.Supplier;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String source1, String source2, String destination) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(createSupplier(source1));
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(createSupplier(source2));

        return future1.thenCombineAsync(future2, (string1, string2) -> {
            try {
                var path = Path.of(destination);
                var options = new OpenOption[] {StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING};
                Files.writeString(path, string1, options);
                Files.writeString(path, string2, StandardOpenOption.APPEND);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            return "";
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });
    }

    private static Supplier<String> createSupplier(String fileName) {
        return () -> {
            try {
                return Files.readString(Path.of(fileName));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    public static CompletableFuture<Long> getDirectorySize(String directoryName) {
        return CompletableFuture.supplyAsync(() -> {
            try (var dir = Files.newDirectoryStream(Path.of(directoryName))) {
                var iterator = dir.iterator();
                var result = 0L;

                while (iterator.hasNext()) {
                    var path = iterator.next();
                    if (Files.isRegularFile(path)) {
                        result += Files.size(path);
                    }
                }

                return result;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> feature = App.unionFiles(
            "src/main/resources/file1.txt",
            "src/main/resources/file2.txt",
            "src/main/resources/dest.txt"
        );

        feature.get();
        // END
    }
}

