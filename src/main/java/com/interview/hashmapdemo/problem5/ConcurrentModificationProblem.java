package com.interview.hashmapdemo.problem5;

import com.interview.hashmapdemo.HashMapProblem;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentModificationProblem implements HashMapProblem {
    @Override
    public String getProblemName() {
        return "Concurrent modification";
    }

    @Override
    public String getProblemDescription() {
        return "A HashMap is modified by multiple threads, causing " +
                "data corruption or infinite loops.\n" +
                "Cause: HashMap is not thread-safe.";
    }

    @Override
    public void demonstrate() {
        // Thread-unsafe HashMap
        final Map<Integer, String> unsafeMap = new HashMap<>();

        // Thread-safe HashMap
        final Map<Integer, String> safeMap = new ConcurrentHashMap<>();

        // Initial population
        for (int i = 0; i < 100; i++) {
            unsafeMap.put(i, "Value " + i);
            safeMap.put(i, "Value " + i);
        }

        // Modify from multiple threads
        ExecutorService executor = Executors.newFixedThreadPool(10);

        System.out.println("Running concurrent operations...");

        try {
            // Concurrent modifications on the thread-unsafe HashMap
            for (int i = 0; i < 10; i++) {
                final int base = i * 100;
                executor.submit(() -> {
                    try {
                        for (int j = 0; j < 100; j++) {
                            unsafeMap.put(base + j, "Thread " + base + "-" + j);
                            // Simulate some work
                            Thread.sleep(1);
                        }
                    } catch (Exception e) {
                        System.out.println("Error in unsafeMap: " + e.getMessage());
                    }
                });
            }

            // Concurrent read
            executor.submit(() -> {
                try {
                    for (Map.Entry<Integer, String> entry : unsafeMap.entrySet()) {
                        // Read-only
                        String value = entry.getValue();
                    }
                } catch (Exception e) {
                    System.out.println("Error iterating over unsafeMap: " + e.getMessage());
                }
            });

            // Same pattern with ConcurrentHashMap
            for (int i = 0; i < 10; i++) {
                final int base = i * 100;
                executor.submit(() -> {
                    try {
                        for (int j = 0; j < 100; j++) {
                            safeMap.put(base + j, "Thread " + base + "-" + j);
                            // Simulate some work
                            Thread.sleep(1);
                        }
                    } catch (Exception e) {
                        System.out.println("Error in safeMap: " + e.getMessage());
                    }
                });
            }

            // Reading the thread-safe map
            executor.submit(() -> {
                try {
                    for (Map.Entry<Integer, String> entry : safeMap.entrySet()) {
                        // Read-only
                        String value = entry.getValue();
                    }
                } catch (Exception e) {
                    System.out.println("Error iterating over safeMap: " + e.getMessage());
                    e.printStackTrace();
                }
            });

            // Wait for completion or timeout
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);

        } catch (Exception e) {
            System.out.println("Main exception: " + e.getMessage());
        }

        System.out.println("Size of unsafeMap after concurrency: " + unsafeMap.size());
        System.out.println("Size of safeMap after concurrency: " + safeMap.size());
    }
}
