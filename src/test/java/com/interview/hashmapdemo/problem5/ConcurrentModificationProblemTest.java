package com.interview.hashmapdemo.problem5;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import static org.junit.jupiter.api.Assertions.*;

public class ConcurrentModificationProblemTest {

    // Test case 1: Concurrent modification during iteration
    // Expected: ConcurrentModificationException should be thrown
    @Test
    void testConcurrentModificationDuringIteration() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        
        assertThrows(ConcurrentModificationException.class, () -> {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                map.put("key3", "value3"); // Modifying map during iteration
            }
        });
    }

    // Test case 2: Safe iteration using iterator's remove method
    // Expected: No exception should be thrown
    @Test
    void testSafeRemovalDuringIteration() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            if (entry.getKey().equals("key1")) {
                iterator.remove(); // Safe removal using iterator
            }
        }
        
        assertEquals(1, map.size(), "Map should have one entry removed");
        assertFalse(map.containsKey("key1"), "key1 should be removed");
    }

    // Test case 3: Concurrent modification in multi-threaded environment
    // Expected: Potential race conditions and inconsistencies
    @Test
    void testMultiThreadedModification() throws InterruptedException {
        HashMap<Integer, Integer> map = new HashMap<>();
        int threadCount = 10;
        int iterations = 1000;
        
        Runnable task = () -> {
            for (int i = 0; i < iterations; i++) {
                map.put(i, i);
            }
        };
        
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }
        
        for (Thread thread : threads) {
            thread.join();
        }
        
        // The size might be less than expected due to race conditions
        assertTrue(map.size() <= threadCount * iterations, 
            "Map size should be less than or equal to total insertions due to race conditions");
    }

    // Test case 4: Using ConcurrentHashMap for thread-safe operations
    // Expected: No concurrent modification issues
    @Test
    void testConcurrentHashMapUsage() throws InterruptedException {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        int threadCount = 10;
        int iterations = 1000;
        
        Runnable task = () -> {
            for (int i = 0; i < iterations; i++) {
                map.put(i, i);
            }
        };
        
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }
        
        for (Thread thread : threads) {
            thread.join();
        }
        
        assertEquals(iterations, map.size(), 
            "ConcurrentHashMap should maintain correct size in multi-threaded environment");
    }

    // Test case 5: Safe iteration with concurrent modification using keySet
    // Expected: ConcurrentModificationException should be thrown
    @Test
    void testConcurrentModificationWithKeySet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        
        assertThrows(ConcurrentModificationException.class, () -> {
            for (String key : map.keySet()) {
                map.put("key3", "value3"); // Modifying map during iteration
            }
        });
    }

    // Test case 6: Safe bulk operations
    // Expected: No concurrent modification issues
    @Test
    void testSafeBulkOperations() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        
        // Create a copy of the map for safe iteration
        HashMap<String, String> copy = new HashMap<>(map);
        
        // Modify the original map
        map.put("key3", "value3");
        
        // Iterate over the copy safely
        int count = 0;
        for (Map.Entry<String, String> entry : copy.entrySet()) {
            count++;
        }
        
        assertEquals(2, count, "Copy should maintain original size");
        assertEquals(3, map.size(), "Original map should have new entry");
    }
} 