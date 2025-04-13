package com.interview.hashmapdemo.problem7;

import com.interview.hashmapdemo.HashMapProblem;
import java.util.HashMap;

public class LoadFactorProblem implements HashMapProblem {
    private HashMap<Integer, String> map;
    private float loadFactor;
    private int initialCapacity;

    public LoadFactorProblem(int initialCapacity, float loadFactor) {
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.map = new HashMap<>(initialCapacity, loadFactor);
    }

    @Override
    public void demonstrate() {
        System.out.println("\n=== Load Factor and Performance Problem ===");
        System.out.println("Initial Capacity: " + initialCapacity);
        System.out.println("Load Factor: " + loadFactor);
        
        // Fill the map to trigger resizing
        int resizeThreshold = (int) (initialCapacity * loadFactor);
        System.out.println("Resize Threshold: " + resizeThreshold);
        
        // Fill up to threshold - 1
        for (int i = 0; i < resizeThreshold - 1; i++) {
            map.put(i, "value" + i);
        }
        System.out.println("Size before resize: " + map.size());
        
        // Add one more to trigger resize
        map.put(resizeThreshold, "value" + resizeThreshold);
        System.out.println("Size after resize: " + map.size());
        
        // Demonstrate performance impact
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            map.get(i % map.size());
        }
        long endTime = System.nanoTime();
        System.out.println("Time for 1000 lookups: " + (endTime - startTime) + " ns");
    }

    @Override
    public String getProblemName() {
        return "Load Factor and Performance Problem";
    }

    @Override
    public String getProblemDescription() {
        return "Demonstrates how load factor and initial capacity affect HashMap performance and memory usage.";
    }
} 