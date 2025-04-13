package com.interview.hashmapdemo.problem8;

import com.interview.hashmapdemo.HashMapProblem;
import java.util.*;

public class KeyOrderingProblem implements HashMapProblem {
    private Map<Integer, String> hashMap;
    private Map<Integer, String> linkedHashMap;
    private Map<Integer, String> treeMap;

    public KeyOrderingProblem() {
        this.hashMap = new HashMap<>();
        this.linkedHashMap = new LinkedHashMap<>();
        this.treeMap = new TreeMap<>();
    }

    @Override
    public void demonstrate() {
        System.out.println("\n=== Key Ordering Problem ===");
        
        // Insert elements in random order
        int[] keys = {3, 1, 4, 2, 5};
        for (int key : keys) {
            String value = "value" + key;
            hashMap.put(key, value);
            linkedHashMap.put(key, value);
            treeMap.put(key, value);
        }

        // Demonstrate HashMap ordering (no guaranteed order)
        System.out.println("\nHashMap Ordering (no guaranteed order):");
        for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Demonstrate LinkedHashMap ordering (insertion order)
        System.out.println("\nLinkedHashMap Ordering (insertion order):");
        for (Map.Entry<Integer, String> entry : linkedHashMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Demonstrate TreeMap ordering (natural order)
        System.out.println("\nTreeMap Ordering (natural order):");
        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Demonstrate access order in LinkedHashMap
        System.out.println("\nLinkedHashMap with access order:");
        Map<Integer, String> accessOrderMap = new LinkedHashMap<>(16, 0.75f, true);
        for (int key : keys) {
            accessOrderMap.put(key, "value" + key);
        }
        
        // Access some elements
        accessOrderMap.get(1);
        accessOrderMap.get(3);
        
        System.out.println("Order after accessing elements 1 and 3:");
        for (Map.Entry<Integer, String> entry : accessOrderMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    @Override
    public String getProblemName() {
        return "Key Ordering Problem";
    }

    @Override
    public String getProblemDescription() {
        return "Demonstrates different Map implementations and their ordering characteristics.";
    }
} 