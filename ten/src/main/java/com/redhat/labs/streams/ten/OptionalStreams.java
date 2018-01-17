package com.redhat.labs.streams.ten;

import java.util.Optional;
import java.util.stream.Stream;

public class OptionalStreams {

    /**
     * Iterate the stream, filtering out the empty {@link Optional}s and printing the content of the
     * {@link Optional}s containing actual values.
     * @param args The command-line arguments (ignored)
     */
    public static void main(String[] args) {
        Stream<Optional<String>> stream = getStringStream(30);

        stream
            .parallel()
            .filter(Optional::isPresent)
            .map(Optional::get)
            .forEach(System.out::println);

        // Streams CANNOT BE REUSED!!!
        // stream
        //            .filter(o -> o.isPresent())
        //            .map(o -> o.get())
        //            .forEach(System.out::println);
        //
        // This would generate an IllegalStateException because the stream has already been processed
    }

    /**
     * Create a stream of randomly populated and empty {@link Optional}s based on a random numbers
     * @param end The maximum number of items to generate in the stream
     * @return A {@link Stream} of {@link Optional<String>}
     */
    public static Stream<Optional<String>> getStringStream(int end) {
        return Stream.generate(Math::random).limit(end).map(i -> {
            if (i<0.5) {
                return Optional.of("a");
            } else {
                return Optional.empty();
            }
        });
    }
}
