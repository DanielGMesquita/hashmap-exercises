package com.interview.hashmapdemo.problem9;

import com.interview.hashmapdemo.HashMapProblem;
import java.io.*;
import java.util.HashMap;

public class SerializationProblem implements HashMapProblem {
    private HashMap<String, String> originalMap;
    private HashMap<String, StringBuilder> mutableMap;

    public SerializationProblem() {
        this.originalMap = new HashMap<>();
        this.mutableMap = new HashMap<>();
        
        // Initialize maps
        originalMap.put("key1", "value1");
        originalMap.put("key2", "value2");
        
        mutableMap.put("key1", new StringBuilder("value1"));
        mutableMap.put("key2", new StringBuilder("value2"));
    }

    @Override
    public void demonstrate() {
        System.out.println("\n=== Serialization and Cloning Problem ===");
        
        try {
            // Demonstrate basic serialization
            System.out.println("\n1. Basic Serialization:");
            byte[] serializedData = serializeMap(originalMap);
            HashMap<String, String> deserializedMap = deserializeMap(serializedData);
            System.out.println("Original map equals deserialized map: " + 
                originalMap.equals(deserializedMap));

            // Demonstrate cloning (shallow copy)
            System.out.println("\n2. HashMap Cloning (Shallow Copy):");
            @SuppressWarnings("unchecked")
            HashMap<String, StringBuilder> clonedMap = 
                (HashMap<String, StringBuilder>) mutableMap.clone();
            
            // Modify the original map's value
            mutableMap.get("key1").append(" modified");
            System.out.println("Cloned map value after modifying original: " + 
                clonedMap.get("key1"));

            // Demonstrate deep copy
            System.out.println("\n3. Deep Copy:");
            HashMap<String, StringBuilder> deepCopy = new HashMap<>();
            for (HashMap.Entry<String, StringBuilder> entry : mutableMap.entrySet()) {
                deepCopy.put(entry.getKey(), new StringBuilder(entry.getValue().toString()));
            }
            
            // Modify the original map's value
            mutableMap.get("key1").append(" again");
            System.out.println("Deep copy value after modifying original: " + 
                deepCopy.get("key1"));
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during serialization/deserialization: " + e.getMessage());
        }
    }

    private byte[] serializeMap(HashMap<String, String> map) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(map);
        oos.close();
        return baos.toByteArray();
    }

    @SuppressWarnings("unchecked")
    private HashMap<String, String> deserializeMap(byte[] data) 
            throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (HashMap<String, String>) ois.readObject();
    }

    @Override
    public String getProblemName() {
        return "Serialization and Cloning Problem";
    }

    @Override
    public String getProblemDescription() {
        return "Demonstrates HashMap serialization, cloning, and deep copying.";
    }
} 