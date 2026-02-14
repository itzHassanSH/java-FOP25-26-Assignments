package h13.csv;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Provides a class method to parse CSV data
 */
public final class CSVParser {

    @DoNotTouch
    private CSVParser() {
    }

    /**
     * Reads and parses CSV data from the given {@link BufferedReader}.
     * Blank lines are skipped and whitespaces around single data cells are trimmed.
     * Each row is split at ',' and then converted to an object representing that row
     * using the provided rowDeserializer.
     *
     * @param reader          reader to read text data from
     * @param rowDeserializer a function that converts ONE ','-split row into an object representing that row
     * @param <R>             the type of object to represent one row
     * @return the parsed table as a list of row-objects
     * @throws IOException if an I/O error occurs while reading from reader
     */
    @StudentImplementationRequired("H13.2.1")
    public static <R> List<R> parse(BufferedReader reader, Function<List<String>, R> rowDeserializer) throws IOException {
        // TODO: H13.2.1
        return reader.lines().filter(s -> !s.isEmpty()).map(String::trim)
            .map(s -> Arrays.stream(s.split(",")).toList()).map(rowDeserializer).toList();
    }
}
