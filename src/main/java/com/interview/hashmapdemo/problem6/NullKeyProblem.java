package com.interview.hashmapdemo.problem6;

import com.interview.hashmapdemo.HashMapProblem;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class NullKeyProblem implements HashMapProblem {
    @Override
    public String getProblemName() {
        return "Null key collisions";
    }

    @Override
    public String getProblemDescription() {
        return "Null keys are causing unexpected behavior or " +
                "exceptions in custom map implementations.\n" +
                "Cause: While HashMap allows one null key, " +
                "custom maps or Hashtable do not.";
    }

    @Override
    public void demonstrate() {
        // HashMap allows a null key
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(null, "Value for null key");
        System.out.println("HashMap with null key: " + hashMap.get(null));

        // Hashtable does not allow null keys
        Hashtable<String, String> hashtable = new Hashtable<>();
        try {
            hashtable.put(null, "Value for null key");
        } catch (Exception e) {
            System.out.println("Hashtable with null key: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // ConcurrentHashMap also does not allow null keys
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        try {
            concurrentHashMap.put(null, "Value for null key");
        } catch (Exception e) {
            System.out.println("ConcurrentHashMap with null key: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // Custom Map implementation with null check
        class CustomMap<K, V> extends HashMap<K, V> {
            @Override
            public V put(K key, V value) {
                if (key == null) {
                    throw new NullPointerException("CustomMap does not allow null keys");
                }
                return super.put(key, value);
            }
        }

        CustomMap<String, String> customMap = new CustomMap<>();
        try {
            customMap.put(null, "Value for null key");
        } catch (Exception e) {
            System.out.println("CustomMap with null key: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}
