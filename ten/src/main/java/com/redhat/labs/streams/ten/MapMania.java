package com.redhat.labs.streams.ten;

import java.text.Collator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class MapMania {

    public static void main(String[] args) {
        Map<String, String> data = new HashMap<>();
        data.put("Joe", "Capricorn");
        data.put("Jane", "Pisces");
        data.put("Dan", "Sagittarius");
        data.put("Nicole", "Leo");
        data.put("Jim", "Virgo");
        data.put("Anna", "Cancer");

        // Create a new ForkJoinPool with only 4 threads
        ForkJoinPool customThreadPool = new ForkJoinPool(4);

        // Run the parallelStream on that new ForkJoinPool
        try {
            customThreadPool.submit(() -> {
                        return data.entrySet()
                                .parallelStream()
                                .map(MapMania::formatSigns)
                                .map(MapMania::someExpensiveOperation)  // Run your expensive operations on the default ForkJoinPool
                                .collect(Collectors.toList());
                    }
            ).get().stream()    // Retrieve the resultant list and stream it
                    .sorted((s1, s2) -> Collator.getInstance().compare(s1, s2))
                    .forEach(System.out::println);
        } catch (InterruptedException|ExecutionException ie) {
            // Ignore this
        }
    }

    // Fake an expensive operation which blocks or takes time.
    private static String someExpensiveOperation(String s) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            // Ignore this
        }
        return s;
    }

    private static String formatSigns(Map.Entry<String, String> e) {
        return String.format("%s is a %s", e.getKey(), e.getValue());
    }
}
