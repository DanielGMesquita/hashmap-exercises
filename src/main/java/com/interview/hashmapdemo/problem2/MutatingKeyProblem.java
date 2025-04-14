package com.interview.hashmapdemo.problem2;

import com.interview.hashmapdemo.HashMapProblem;

import java.util.HashMap;
import java.util.Objects;

public class MutatingKeyProblem implements HashMapProblem {
    @Override
    public String getProblemName() {
        return "Mutating objects after insertion into HashMap";
    }

    @Override
    public String getProblemDescription() {
        return "A key object is modified after being inserted into the HashMap.\n" +
                "This can lead to unexpected behavior when trying to retrieve values using the modified key.\n" +
                "The key's hash code and equality check may no longer match the original insertion.";
    }

    @Override
    public void demonstrate() {
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
                MutableKey that = (MutableKey) obj;
                return Objects.equals(value, that.value);
            }

            @Override
            public int hashCode() {
                return Objects.hash(value);
            }

            @Override
            public String toString() {
                return "MutableKey{" + value + '}';
            }
        }

        HashMap<MutableKey, String> map = new HashMap<>();

        MutableKey key = new MutableKey("original");
        map.put(key, "original value");

        System.out.println("Value before mutation: " + map.get(key));

        // Change the key after insertion
        key.setValue("modified");
        System.out.println("Value after mutation: " + map.get(key));

        // Create a new key with the same value as the original
        MutableKey originalKey = new MutableKey("original");
        System.out.println("Can it retrieve the value using equivalent key to the original value? " +
                (map.get(originalKey) != null));

        // Demonstration of the problem
        System.out.println("Map size: " + map.size());
        System.out.println("Map content: " + map);
    }
}
