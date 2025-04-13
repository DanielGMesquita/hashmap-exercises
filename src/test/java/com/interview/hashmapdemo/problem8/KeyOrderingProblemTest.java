package com.interview.hashmapdemo.problem8;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class KeyOrderingProblemTest {

    // Test case 1: HashMap does not maintain insertion order
    // Expected: HashMap entries may appear in any order
    @Test
    void testHashMapOrdering() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(3, "three");
        map.put(1, "one");
        map.put(2, "two");
        
        // Convert to list to check order
        List<Integer> keys = new ArrayList<>(map.keySet());
        
        // HashMap doesn't guarantee order, so we can't make specific assertions
        // about the order of elements
        assertEquals(3, keys.size(), "Map should contain all keys");
        assertTrue(keys.contains(1), "Map should contain key 1");
        assertTrue(keys.contains(2), "Map should contain key 2");
        assertTrue(keys.contains(3), "Map should contain key 3");
    }

    // Test case 2: LinkedHashMap maintains insertion order
    // Expected: LinkedHashMap entries appear in insertion order
    @Test
    void testLinkedHashMapOrdering() {
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
        map.put(3, "three");
        map.put(1, "one");
        map.put(2, "two");
        
        List<Integer> expectedOrder = Arrays.asList(3, 1, 2);
        List<Integer> actualOrder = new ArrayList<>(map.keySet());
        
        assertEquals(expectedOrder, actualOrder, 
            "LinkedHashMap should maintain insertion order");
    }

    // Test case 3: LinkedHashMap with access order
    // Expected: Most recently accessed entries move to the end
    @Test
    void testLinkedHashMapAccessOrder() {
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>(16, 0.75f, true);
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        
        // Access some entries
        map.get(1);
        map.get(2);
        
        List<Integer> expectedOrder = Arrays.asList(3, 1, 2);
        List<Integer> actualOrder = new ArrayList<>(map.keySet());
        
        assertEquals(expectedOrder, actualOrder, 
            "LinkedHashMap should reorder based on access");
    }

    // Test case 4: TreeMap maintains natural ordering
    // Expected: TreeMap entries appear in natural order
    @Test
    void testTreeMapOrdering() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(3, "three");
        map.put(1, "one");
        map.put(2, "two");
        
        List<Integer> expectedOrder = Arrays.asList(1, 2, 3);
        List<Integer> actualOrder = new ArrayList<>(map.keySet());
        
        assertEquals(expectedOrder, actualOrder, 
            "TreeMap should maintain natural ordering");
    }

    // Test case 5: Custom ordering with TreeMap
    // Expected: TreeMap entries appear in custom order
    @Test
    void testTreeMapCustomOrdering() {
        TreeMap<String, Integer> map = new TreeMap<>(Comparator.reverseOrder());
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);
        
        List<String> expectedOrder = Arrays.asList("cherry", "banana", "apple");
        List<String> actualOrder = new ArrayList<>(map.keySet());
        
        assertEquals(expectedOrder, actualOrder, 
            "TreeMap should maintain custom ordering");
    }
} 