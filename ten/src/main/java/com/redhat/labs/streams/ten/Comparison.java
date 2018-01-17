package com.redhat.labs.streams.ten;

import java.util.stream.Stream;

public class Comparison {

    public static void main(String[] args) {
        // Old School
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }

        // Using for-each style loop
        for (String arg : args) {
            System.out.println(arg);
        }

        // Using Streams
        Stream
                .of(args)
                .forEach(System.out::println);
    }
}
