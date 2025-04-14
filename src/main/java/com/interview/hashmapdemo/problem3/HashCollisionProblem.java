package com.interview.hashmapdemo.problem3;

import com.interview.hashmapdemo.HashMapProblem;

import java.util.HashMap;
import java.util.Objects;

public class HashCollisionProblem implements HashMapProblem {
    @Override
    public String getProblemName() {
        return "Hash collisions and poor distribution";
    }

    @Override
    public String getProblemDescription() {
        return "Multiple keys are placed in the same bucket, degrading " +
                "performance from O(1) to O(n).\n" +
                "Cause: A poorly designed hashCode() function " +
                "(for example, always returning the same value).";
    }

    @Override
    public void demonstrate() {
        class BadHashKey {
            private String value;

            public BadHashKey(String value) {
                this.value = value;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                BadHashKey that = (BadHashKey) obj;
                return Objects.equals(value, that.value);
            }

            @Override
            public int hashCode() {
                return Objects.hash(value);
            }

            @Override
            public String toString() {
                return value;
            }
        }

        // Demonstrate performance with good and bad keys
        int itemCount = 10000;

        // 1. Using String (good hash distribution)
        HashMap<String, Integer> goodMap = new HashMap<>();
        long startGood = System.nanoTime();
        for (int i = 0; i < itemCount; i++) {
            goodMap.put("key" + i, i);
        }
        // Look up a specific value
        long lookupStartGood = System.nanoTime();
        goodMap.containsKey("key" + (itemCount - 1));
        long lookupEndGood = System.nanoTime();

        // 2. Using keys with bad hash
        HashMap<BadHashKey, Integer> badMap = new HashMap<>();
        long startBad = System.nanoTime();
        for (int i = 0; i < itemCount; i++) {
            badMap.put(new BadHashKey("key" + i), i);
        }
        // Look up a specific value
        long lookupStartBad = System.nanoTime();
        badMap.containsKey(new BadHashKey("key" + (itemCount - 1)));
        long lookupEndBad = System.nanoTime();

        System.out.println("Lookup time with good keys (ns): " + (lookupEndGood - lookupStartGood));
        System.out.println("Lookup time with bad keys (ns): " + (lookupEndBad - lookupStartBad));
        System.out.println("Ratio: " +
                (double)(lookupEndBad - lookupStartBad) / (lookupEndGood - lookupStartGood));
    }
}
