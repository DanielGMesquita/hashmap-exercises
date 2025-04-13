package com.interview.hashmapdemo.problem2;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

public class MutatingKeyProblemTest {

    // Test case 1: Mutating a key after it's been used in HashMap
    // Expected: HashMap becomes inconsistent when key is mutated
    @Test
    void testMutatingKeyAfterInsertion() {
        class MutableKey {
            private String value;

            public MutableKey(String value) {
                this.value = value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                MutableKey key = (MutableKey) obj;
                return value.equals(key.value);
            }

            @Override
            public int hashCode() {
                return value.hashCode();
            }
        }

        HashMap<MutableKey, String> map = new HashMap<>();
        MutableKey key = new MutableKey("original");
        
        map.put(key, "value1");
        assertEquals("value1", map.get(key), "Should get original value");
        
        key.setValue("modified");
        assertNull(map.get(key), "Should not find value after key mutation");
        assertFalse(map.containsKey(key), "Should not contain key after mutation");
        
        // Original value is still in the map but inaccessible
        assertEquals(1, map.size(), "Map size should remain unchanged");
    }

    // Test case 2: Using mutable objects as keys
    // Expected: HashMap becomes inconsistent when objects are modified
    @Test
    void testMutableObjectsAsKeys() {
        class MutablePerson {
            private String name;
            private int age;

            public MutablePerson(String name, int age) {
                this.name = name;
                this.age = age;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setAge(int age) {
                this.age = age;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                MutablePerson person = (MutablePerson) obj;
                return age == person.age && name.equals(person.name);
            }

            @Override
            public int hashCode() {
                return 31 * name.hashCode() + age;
            }
        }

        HashMap<MutablePerson, String> map = new HashMap<>();
        MutablePerson person = new MutablePerson("John", 30);
        
        map.put(person, "Developer");
        assertEquals("Developer", map.get(person), "Should get original value");
        
        person.setName("Johnny");
        assertNull(map.get(person), "Should not find value after name change");
        
        person.setAge(31);
        assertNull(map.get(person), "Should not find value after age change");
    }

    // Test case 3: Using immutable objects as keys
    // Expected: HashMap remains consistent as keys cannot be modified
    @Test
    void testImmutableObjectsAsKeys() {
        class ImmutablePerson {
            private final String name;
            private final int age;

            public ImmutablePerson(String name, int age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                ImmutablePerson person = (ImmutablePerson) obj;
                return age == person.age && name.equals(person.name);
            }

            @Override
            public int hashCode() {
                return 31 * name.hashCode() + age;
            }
        }

        HashMap<ImmutablePerson, String> map = new HashMap<>();
        ImmutablePerson person1 = new ImmutablePerson("Mary", 28);
        ImmutablePerson person2 = new ImmutablePerson("Mary", 28);
        
        map.put(person1, "Designer");
        assertEquals("Designer", map.get(person2), "Should find value with equal immutable key");
        assertTrue(map.containsKey(person2), "Should contain equal immutable key");
    }

    // Test case 4: Using mutable collections as keys
    // Expected: HashMap becomes inconsistent when collections are modified
    @Test
    void testMutableCollectionsAsKeys() {
        class CollectionKey {
            private java.util.List<String> items;

            public CollectionKey(java.util.List<String> items) {
                this.items = new java.util.ArrayList<>(items);
            }

            public void addItem(String item) {
                items.add(item);
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                CollectionKey key = (CollectionKey) obj;
                return items.equals(key.items);
            }

            @Override
            public int hashCode() {
                return items.hashCode();
            }
        }

        HashMap<CollectionKey, String> map = new HashMap<>();
        CollectionKey key = new CollectionKey(java.util.Arrays.asList("a", "b"));
        
        map.put(key, "value");
        assertEquals("value", map.get(key), "Should get original value");
        
        key.addItem("c");
        assertNull(map.get(key), "Should not find value after collection modification");
    }
} 