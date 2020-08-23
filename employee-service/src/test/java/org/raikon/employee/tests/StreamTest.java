package org.raikon.employee.tests;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    @Test
    public void test() throws IOException {
        final var test = Files.list(Paths.get("C:\\Users\\eduardo.lopes\\IdeaProjects\\employee\\employee-service\\src\\test\\java\\br\\com\\mundiale\\employee\\fake"))
                .filter(path -> path.toString().contains("Address"))
                .flatMap(StreamTest::lines)
                .collect(Collectors.partitioningBy(Strings::isBlank));

        System.out.println(test.get(true));
    }

    private static Stream<String> lines(Path path) {
        try {
            return Files.lines(path);
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
}
