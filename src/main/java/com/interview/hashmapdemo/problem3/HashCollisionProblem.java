package com.interview.hashmapdemo.problem3;

import com.interview.hashmapdemo.HashMapProblem;

import java.util.HashMap;
import java.util.Objects;

public class HashCollisionProblem implements HashMapProblem {
    @Override
    public String getProblemName() {
        return "Colisões de hash e má distribuição";
    }

    @Override
    public String getProblemDescription() {
        return "Múltiplas chaves são colocadas no mesmo bucket, degradando " +
                "o desempenho de O(1) para O(n).\n" +
                "Causa: Uma função hashCode() mal projetada " +
                "(por exemplo, sempre retornando o mesmo valor).";
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
                // Péssima implementação: sempre retorna o mesmo valor!
                return 42;
            }

            @Override
            public String toString() {
                return value;
            }
        }

        // Demonstrar desempenho com boas e más chaves
        int itemCount = 10000;

        // 1. Usando String (boa distribuição de hash)
        HashMap<String, Integer> goodMap = new HashMap<>();
        long startGood = System.nanoTime();
        for (int i = 0; i < itemCount; i++) {
            goodMap.put("key" + i, i);
        }
        // Procurar um valor específico
        long lookupStartGood = System.nanoTime();
        goodMap.containsKey("key" + (itemCount - 1));
        long lookupEndGood = System.nanoTime();

        // 2. Usando chaves com hash ruim
        HashMap<BadHashKey, Integer> badMap = new HashMap<>();
        long startBad = System.nanoTime();
        for (int i = 0; i < itemCount; i++) {
            badMap.put(new BadHashKey("key" + i), i);
        }
        // Procurar um valor específico
        long lookupStartBad = System.nanoTime();
        badMap.containsKey(new BadHashKey("key" + (itemCount - 1)));
        long lookupEndBad = System.nanoTime();

        System.out.println("Tempo de busca com boas chaves (ns): " + (lookupEndGood - lookupStartGood));
        System.out.println("Tempo de busca com chaves ruins (ns): " + (lookupEndBad - lookupStartBad));
        System.out.println("Proporção: " +
                (double)(lookupEndBad - lookupStartBad) / (lookupEndGood - lookupStartGood));
    }
}
