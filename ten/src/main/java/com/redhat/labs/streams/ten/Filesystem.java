package com.redhat.labs.streams.ten;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

public class Filesystem {

    public static void main(String[] args) throws Exception {

        // Create a stream of files whose names end with ".xml"
        try (Stream<Path> files = Files.find(Paths.get("."), Integer.MAX_VALUE, Filesystem::isXmlFile)) {

            // Create a stream of lines from each file and filter for the regex ".*module.*"
            files
                .filter(f -> f.toFile().isFile())
                .flatMap(f -> {
                    try {
                        return Files
                                .lines(f)
                                .filter(l -> l.matches(".*module.*"))
                                .map(l -> f.toString()+":"+l);  // Prepend the file name and path
                    } catch (IOException ioe) {
                        return Stream.empty();  // If there is a problem return an empty Stream
                    }
                })
                .forEach(System.out::println);  // Print each line which contains the matched regex
        }
    }

    private static boolean isXmlFile(Path filePath, BasicFileAttributes fileAttr) {
        return filePath.toString().endsWith(".xml");
    }
}
