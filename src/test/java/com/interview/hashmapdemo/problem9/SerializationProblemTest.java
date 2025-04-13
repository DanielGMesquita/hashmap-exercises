package com.interview.hashmapdemo.problem9;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class SerializationProblemTest {

    // Test case 1: Basic HashMap serialization
    // Expected: HashMap should be properly serialized and deserialized
    @Test
    void testBasicSerialization() throws IOException, ClassNotFoundException {
        HashMap<String, String> originalMap = new HashMap<>();
        originalMap.put("key1", "value1");
        originalMap.put("key2", "value2");
        
        // Serialize
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(originalMap);
        oos.close();
        
        // Deserialize
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        @SuppressWarnings("unchecked")
        HashMap<String, String> deserializedMap = (HashMap<String, String>) ois.readObject();
        
        assertEquals(originalMap, deserializedMap, 
            "Deserialized map should be equal to original map");
    }

    // Test case 2: HashMap with custom objects
    // Expected: Custom objects must implement Serializable
    @Test
    void testCustomObjectSerialization() throws IOException, ClassNotFoundException {
        HashMap<PersonForTest, String> originalMap = new HashMap<>();
        PersonForTest person = new PersonForTest("John", 30);
        originalMap.put(person, "Developer");
        
        // Serialize
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(originalMap);
        oos.close();
        
        // Deserialize
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        @SuppressWarnings("unchecked")
        HashMap<PersonForTest, String> deserializedMap = (HashMap<PersonForTest, String>) ois.readObject();
        
        assertEquals(originalMap, deserializedMap, 
            "Deserialized map with custom objects should be equal to original map");
    }

    // Test case 3: HashMap cloning
    // Expected: Clone should create a shallow copy
    @Test
    void testHashMapCloning() {
        HashMap<String, StringBuilder> originalMap = new HashMap<>();
        StringBuilder value = new StringBuilder("value");
        originalMap.put("key", value);
        
        @SuppressWarnings("unchecked")
        HashMap<String, StringBuilder> clonedMap = (HashMap<String, StringBuilder>) originalMap.clone();
        
        // Modify the value in the original map
        value.append(" modified");
        
        assertEquals("value modified", clonedMap.get("key").toString(), 
            "Cloned map should reflect changes to shared objects");
    }

    // Test case 4: Deep copy of HashMap
    // Expected: Deep copy should create independent copies of all objects
    @Test
    void testDeepCopy() {
        HashMap<String, StringBuilder> originalMap = new HashMap<>();
        originalMap.put("key1", new StringBuilder("value1"));
        originalMap.put("key2", new StringBuilder("value2"));
        
        HashMap<String, StringBuilder> deepCopy = new HashMap<>();
        for (Map.Entry<String, StringBuilder> entry : originalMap.entrySet()) {
            deepCopy.put(entry.getKey(), new StringBuilder(entry.getValue().toString()));
        }
        
        // Modify the original map
        originalMap.get("key1").append(" modified");
        
        assertEquals("value1", deepCopy.get("key1").toString(), 
            "Deep copy should not reflect changes to original map");
    }

    // Test case 5: Serialization with transient fields
    // Expected: Transient fields should not be serialized
    @Test
    void testTransientFields() throws IOException, ClassNotFoundException {
        // Create a standalone serializable class instead of an inner class
        PersonForTest person = new PersonForTest("John", 30);

        HashMap<String, PersonForTest> originalMap = new HashMap<>();
        originalMap.put("key", person);

        // Serialize
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(originalMap);
        oos.close();

        // Deserialize
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        @SuppressWarnings("unchecked")
        HashMap<String, PersonForTest> deserializedMap = (HashMap<String, PersonForTest>) ois.readObject();

        assertEquals(0, deserializedMap.get("key").getAge(),
                "Transient field should not be serialized");
    }

    // Test case 6: Serialization with custom writeObject/readObject
    // Expected: Custom serialization methods should be used
    @Test
    void testCustomSerialization() throws IOException, ClassNotFoundException {
        CustomHashMap<String, String> originalMap = new CustomHashMap<>();
        originalMap.put("key", "value");
        
        // Serialize
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(originalMap);
        oos.close();
        
        // Deserialize
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        @SuppressWarnings("unchecked")
        CustomHashMap<String, String> deserializedMap = (CustomHashMap<String, String>) ois.readObject();
        
        assertEquals("custom", deserializedMap.customField, 
            "Custom serialization should preserve custom field");
    }

    static class PersonForTest implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String name;
        private final transient int age; // transient field

        public PersonForTest(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            PersonForTest person = (PersonForTest) obj;
            return Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }
    }

    static class CustomHashMap<K, V> extends HashMap<K, V> {
        private static final long serialVersionUID = 1L;
        private String customField = "custom";

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.defaultWriteObject();
            out.writeObject(customField);
        }

        @SuppressWarnings("unchecked")
        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            customField = (String) in.readObject();
        }
    }
} 