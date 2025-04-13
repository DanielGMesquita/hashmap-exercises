package com.interview.hashmapdemo.problem3;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

public class HashCollisionProblemTest {

    // Test case 1: Objects with same hash code but different content
    // Expected: Objects should be stored in same bucket but not equal
    @Test
    void testSameHashCodeDifferentContent() {
        class FixedHashCode {
            private String value;

            public FixedHashCode(String value) {
                this.value = value;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                FixedHashCode other = (FixedHashCode) obj;
                return value.equals(other.value);
            }

            @Override
            public int hashCode() {
                return 1; // Always return same hash code
            }
        }

        HashMap<FixedHashCode, String> map = new HashMap<>();
        FixedHashCode key1 = new FixedHashCode("first");
        FixedHashCode key2 = new FixedHashCode("second");
        
        map.put(key1, "value1");
        map.put(key2, "value2");
        
        assertEquals("value1", map.get(key1), "Should get first value");
        assertEquals("value2", map.get(key2), "Should get second value");
        assertFalse(key1.equals(key2), "Objects should not be equal");
    }

    // Test case 2: Poor hash code distribution
    // Expected: Performance degradation due to many collisions
    @Test
    void testPoorHashCodeDistribution() {
        class PoorHashCode {
            private int value;

            public PoorHashCode(int value) {
                this.value = value;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                PoorHashCode other = (PoorHashCode) obj;
                return value == other.value;
            }

            @Override
            public int hashCode() {
                return value % 2; // Only two possible hash codes
            }
        }

        HashMap<PoorHashCode, String> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(new PoorHashCode(i), "value" + i);
        }
        
        assertEquals(100, map.size(), "All entries should be stored");
        assertEquals("value50", map.get(new PoorHashCode(50)), "Should retrieve correct value");
    }

    // Test case 3: Objects with different hash codes but same content
    // Expected: Objects should be equal but stored in different buckets
    @Test
    void testDifferentHashCodeSameContent() {
        class InconsistentHashCode {
            private final String value;
            private static int nextHash = 0;
            private final int myHashCode;

            public InconsistentHashCode(String value) {
                this.value = value;
                this.myHashCode = nextHash++; // Assign and increment in constructor
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                InconsistentHashCode other = (InconsistentHashCode) obj;
                return value.equals(other.value);
            }

            @Override
            public int hashCode() {
                return myHashCode; // Different hash code each time
            }
        }

        HashMap<InconsistentHashCode, String> map = new HashMap<>();
        InconsistentHashCode key1 = new InconsistentHashCode("test");
        int hashCode1 = key1.hashCode();
        InconsistentHashCode key2 = new InconsistentHashCode("test");
        int hashCode2 = key2.hashCode();
        
        map.put(key1, "value");
        assertNotEquals(hashCode1, hashCode2, "Hash codes should be different");
        assertNull(map.get(key2), "Should not find equal object due to different hash codes");
    }

    // Test case 4: Objects with good hash code distribution
    // Expected: Efficient storage and retrieval
    @Test
    void testGoodHashCodeDistribution() {
        class GoodHashCode {
            private String value;

            public GoodHashCode(String value) {
                this.value = value;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                GoodHashCode other = (GoodHashCode) obj;
                return value.equals(other.value);
            }

            @Override
            public int hashCode() {
                return value.hashCode();
            }
        }

        HashMap<GoodHashCode, String> map = new HashMap<>();
        GoodHashCode key1 = new GoodHashCode("first");
        GoodHashCode key2 = new GoodHashCode("second");
        GoodHashCode key3 = new GoodHashCode("first"); // Equal to key1
        
        map.put(key1, "value1");
        map.put(key2, "value2");
        
        assertEquals("value1", map.get(key3), "Should find equal object with good hash code");
        assertEquals("value2", map.get(key2), "Should find different object with good hash code");
    }
} 