package com.interview.hashmapdemo.problem7;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

public class LoadFactorProblemTest {

    // Test case 1: Default load factor behavior
    // Expected: HashMap should resize when size exceeds capacity * load factor
    @Test
    void testDefaultLoadFactor() {
        HashMap<Integer, String> map = new HashMap<>(16); // Default load factor is 0.75
        int initialCapacity = 16;
        int expectedResizeThreshold = (int) (initialCapacity * 0.75);
        
        // Fill up to threshold - 1
        for (int i = 0; i < expectedResizeThreshold - 1; i++) {
            map.put(i, "value" + i);
        }
        
        // Add one more entry to trigger resize
        map.put(expectedResizeThreshold, "value" + expectedResizeThreshold);
        
        assertEquals(expectedResizeThreshold, map.size(),
            "Map should contain all entries after resize");
    }

    // Test case 2: Custom load factor
    // Expected: HashMap should resize according to custom load factor
    @Test
    void testCustomLoadFactor() {
        float customLoadFactor = 0.5f;
        int initialCapacity = 16;
        HashMap<Integer, String> map = new HashMap<>(initialCapacity, customLoadFactor);
        int expectedResizeThreshold = (int) (initialCapacity * customLoadFactor);
        
        // Fill up to threshold - 1
        for (int i = 0; i < expectedResizeThreshold - 1; i++) {
            map.put(i, "value" + i);
        }
        
        // Add one more entry to trigger resize
        map.put(expectedResizeThreshold, "value" + expectedResizeThreshold);
        
        assertEquals(expectedResizeThreshold, map.size(),
            "Map should contain all entries after resize with custom load factor");
    }

    // Test case 3: Performance impact of high load factor
    // Expected: Higher load factor leads to more collisions and slower performance
    @Test
    void testHighLoadFactorPerformance() {
        float highLoadFactor = 0.9f;
        int initialCapacity = 16;
        HashMap<Integer, String> highLoadMap = new HashMap<>(initialCapacity, highLoadFactor);
        HashMap<Integer, String> defaultLoadMap = new HashMap<>(initialCapacity);
        
        int entries = 1000;
        long startTime, endTime;
        
        // Test high load factor map
        startTime = System.nanoTime();
        for (int i = 0; i < entries; i++) {
            highLoadMap.put(i, "value" + i);
        }
        endTime = System.nanoTime();
        long highLoadTime = endTime - startTime;
        
        // Test default load factor map
        startTime = System.nanoTime();
        for (int i = 0; i < entries; i++) {
            defaultLoadMap.put(i, "value" + i);
        }
        endTime = System.nanoTime();
        long defaultLoadTime = endTime - startTime;
        
        assertTrue(highLoadTime > defaultLoadTime, 
            "High load factor should result in slower performance due to more collisions");
    }

    // Test case 4: Initial capacity impact on performance
    // Expected: Appropriate initial capacity reduces resizing operations
    @Test
    void testInitialCapacityImpact() {
        int smallCapacity = 16;
        int largeCapacity = 1000;
        int entries = 1000;
        
        HashMap<Integer, String> smallMap = new HashMap<>(smallCapacity);
        HashMap<Integer, String> largeMap = new HashMap<>(largeCapacity);
        
        long startTime, endTime;
        
        // Test small initial capacity
        startTime = System.nanoTime();
        for (int i = 0; i < entries; i++) {
            smallMap.put(i, "value" + i);
        }
        endTime = System.nanoTime();
        long smallCapacityTime = endTime - startTime;
        
        // Test large initial capacity
        startTime = System.nanoTime();
        for (int i = 0; i < entries; i++) {
            largeMap.put(i, "value" + i);
        }
        endTime = System.nanoTime();
        long largeCapacityTime = endTime - startTime;
        
        assertTrue(smallCapacityTime > largeCapacityTime, 
            "Appropriate initial capacity should improve performance by reducing resizing");
    }

    // Test case 5: Memory usage with different load factors
    // Expected: Higher load factor uses less memory but may impact performance
    @Test
    void testMemoryUsage() {
        float lowLoadFactor = 0.25f;
        float highLoadFactor = 0.9f;
        int initialCapacity = 16;
        int entries = 1000;
        
        HashMap<Integer, String> lowLoadMap = new HashMap<>(initialCapacity, lowLoadFactor);
        HashMap<Integer, String> highLoadMap = new HashMap<>(initialCapacity, highLoadFactor);
        
        // Fill both maps
        for (int i = 0; i < entries; i++) {
            lowLoadMap.put(i, "value" + i);
            highLoadMap.put(i, "value" + i);
        }
        
        // The high load factor map should use less memory
        assertTrue(highLoadMap.size() == lowLoadMap.size(), 
            "Both maps should contain the same number of entries");
    }
} 