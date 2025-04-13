package com.interview.hashmapdemo.problem6;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

public class NullKeyProblemTest {

    // Test case 1: Using null as a key in HashMap
    // Expected: HashMap should accept null keys
    @Test
    void testNullKeyInHashMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put(null, "value");
        
        assertEquals("value", map.get(null), "Should retrieve value for null key");
        assertTrue(map.containsKey(null), "Should contain null key");
    }

    // Test case 2: Multiple null keys in HashMap
    // Expected: Only the last null key value should be retained
    @Test
    void testMultipleNullKeys() {
        HashMap<String, String> map = new HashMap<>();
        map.put(null, "value1");
        map.put(null, "value2");
        
        assertEquals("value2", map.get(null), "Last value should overwrite previous value");
        assertEquals(1, map.size(), "Map should only have one entry");
    }

    // Test case 3: Null key with custom object as value
    // Expected: HashMap should handle null keys with any value type
    @Test
    void testNullKeyWithCustomObject() {
        class Person {
            private String name;
            
            public Person(String name) {
                this.name = name;
            }
            
            @Override
            public String toString() {
                return name;
            }
        }
        
        HashMap<String, Person> map = new HashMap<>();
        Person person = new Person("John");
        map.put(null, person);
        
        assertEquals(person, map.get(null), "Should retrieve custom object for null key");
    }

    // Test case 4: Null key removal
    // Expected: HashMap should properly remove null key entries
    @Test
    void testNullKeyRemoval() {
        HashMap<String, String> map = new HashMap<>();
        map.put(null, "value");
        map.put("key", "value2");
        
        map.remove(null);
        assertNull(map.get(null), "Null key should be removed");
        assertEquals(1, map.size(), "Map should have one remaining entry");
    }

    // Test case 5: Null key in HashMap with custom equals/hashCode
    // Expected: HashMap should handle null keys even with custom implementations
    @Test
    void testNullKeyWithCustomEqualsHashCode() {
        class CustomKey {
            private String value;
            
            public CustomKey(String value) {
                this.value = value;
            }
            
            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                CustomKey key = (CustomKey) obj;
                return value.equals(key.value);
            }
            
            @Override
            public int hashCode() {
                return value.hashCode();
            }
        }
        
        HashMap<CustomKey, String> map = new HashMap<>();
        map.put(null, "value");
        
        assertEquals("value", map.get(null), "Should handle null key with custom key type");
    }

    // Test case 6: Null key with null value
    // Expected: HashMap should handle both null key and null value
    @Test
    void testNullKeyAndValue() {
        HashMap<String, String> map = new HashMap<>();
        map.put(null, null);
        
        assertNull(map.get(null), "Should retrieve null value for null key");
        assertTrue(map.containsKey(null), "Should contain null key");
        assertEquals(1, map.size(), "Map should have one entry");
    }

    // Test case 7: Null key in HashMap with initial capacity
    // Expected: HashMap should handle null keys regardless of initial capacity
    @Test
    void testNullKeyWithInitialCapacity() {
        HashMap<String, String> map = new HashMap<>(16);
        map.put(null, "value");
        
        assertEquals("value", map.get(null), "Should handle null key with initial capacity");
    }
} 