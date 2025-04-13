package com.interview.hashmapdemo.problem4;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

public class EqualsVsDoubleEqualsProblemTest {

    // Test case 1: Using == instead of equals() for object comparison
    // Expected: Objects with same content but different references are not considered equal
    @Test
    void testDoubleEqualsVsEquals() {
        class Person {
            private String name;
            private int age;

            public Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                Person person = (Person) obj;
                return age == person.age && name.equals(person.name);
            }

            @Override
            public int hashCode() {
                return 31 * name.hashCode() + age;
            }
        }

        Person person1 = new Person("John", 30);
        Person person2 = new Person("John", 30);
        
        assertTrue(person1.equals(person2), "Objects should be equal using equals()");
        assertFalse(person1 == person2, "Objects should not be equal using ==");
    }

    // Test case 2: String comparison with == and equals()
    // Expected: String literals might be equal with == due to string pool, but new String objects won't
    @Test
    void testStringComparison() {
        String str1 = "hello";
        String str2 = "hello";
        String str3 = new String("hello");
        
        assertTrue(str1 == str2, "String literals should be equal using ==");
        assertFalse(str1 == str3, "New String object should not be equal using ==");
        assertTrue(str1.equals(str3), "Strings should be equal using equals()");
    }

    // Test case 3: Integer comparison with == and equals()
    // Expected: Small integers might be equal with == due to caching, but larger ones won't
    @Test
    void testIntegerComparison() {
        Integer a = 100;
        Integer b = 100;
        Integer c = 200;
        Integer d = 200;
        
        assertTrue(a == b, "Small integers should be equal using ==");
        assertFalse(c == d, "Larger integers should not be equal using ==");
        assertTrue(c.equals(d), "Integers should be equal using equals()");
    }

    // Test case 4: Custom object comparison in HashMap
    // Expected: HashMap should use equals() for key comparison
    @Test
    void testHashMapKeyComparison() {
        class Key {
            private int value;

            public Key(int value) {
                this.value = value;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                Key key = (Key) obj;
                return value == key.value;
            }

            @Override
            public int hashCode() {
                return value;
            }
        }

        HashMap<Key, String> map = new HashMap<>();
        Key key1 = new Key(1);
        Key key2 = new Key(1);
        
        map.put(key1, "value");
        assertEquals("value", map.get(key2), "HashMap should find equal keys using equals()");
    }

    // Test case 5: Null comparison with == and equals()
    // Expected: Both == and equals() should handle null correctly
    @Test
    void testNullComparison() {
        String str = "test";
        String nullStr = null;
        
        assertFalse(str == null, "String should not be equal to null using ==");
        assertFalse(str.equals(nullStr), "String should not be equal to null using equals()");
        assertTrue(nullStr == null, "null should be equal to null using ==");
    }
} 