package com.redhat.labs.streams.ten;

import java.util.AbstractMap;
import java.util.stream.Stream;

@SuppressWarnings("all")
public class FizzBuzz {

    public static void main(String... args) {
        // Java 8
        Stream.iterate(0, i -> i++).limit(30)
                .map(i -> new AbstractMap.SimpleEntry<>(Integer.valueOf(i), new StringBuilder()))
                .map(e -> {
                    if (e.getKey().intValue()%3 == 0) e.getValue().append("Fizz");
                    return e;
                })
                .map(e -> {
                    if (e.getKey().intValue()%5 == 0) e.getValue().append("Buzz");
                    return e;
                })
                .map(e -> {
                    if (e.getValue().length()==0) e.getValue().append(e.getKey());
                    return e;
                })
                .map(e -> e.getValue().toString())
                .forEach(System.out::println);


        /* Java 10
        var output = IntStream
                .rangeClosed(1, 30)
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(Integer.valueOf(i), new StringBuilder()))
                .map(e -> {
                    if (e.getKey().intValue()%3 == 0) e.getValue().append("Fizz");
                    return e;
                })
                .map(e -> {
                    if (e.getKey().intValue()%5 == 0) e.getValue().append("Buzz");
                    return e;
                })
                .map(e -> {
                    if (e.getValue().length()==0) e.getValue().append(e.getKey());
                    return e;
                })
                .map(e -> e.getValue().toString())
                .collect(Collectors.joining("\n"));

        System.out.println(output);
        */
    }
}
