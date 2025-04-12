package com.interview.hashmapdemo.problem6;

import com.interview.hashmapdemo.HashMapProblem;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class NullKeyProblem implements HashMapProblem {
    @Override
    public String getProblemName() {
        return "Colisões de chave com null";
    }

    @Override
    public String getProblemDescription() {
        return "Chaves null estão causando comportamento inesperado ou " +
                "exceções em implementações personalizadas de mapas.\n" +
                "Causa: Enquanto HashMap permite uma chave null, mapas " +
                "personalizados ou Hashtable não.";
    }

    @Override
    public void demonstrate() {
        // HashMap permite uma chave null
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(null, "Valor para chave null");
        System.out.println("HashMap com chave null: " + hashMap.get(null));

        // Hashtable não permite chaves null
        Hashtable<String, String> hashtable = new Hashtable<>();
        try {
            hashtable.put(null, "Valor para chave null");
        } catch (Exception e) {
            System.out.println("Hashtable com chave null: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // ConcurrentHashMap também não permite chaves null
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        try {
            concurrentHashMap.put(null, "Valor para chave null");
        } catch (Exception e) {
            System.out.println("ConcurrentHashMap com chave null: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // Implementação personalizada de Map com tratamento de null
        class CustomMap<K, V> extends HashMap<K, V> {
            @Override
            public V put(K key, V value) {
                if (key == null) {
                    throw new NullPointerException("CustomMap não permite chaves null");
                }
                return super.put(key, value);
            }
        }

        CustomMap<String, String> customMap = new CustomMap<>();
        try {
            customMap.put(null, "Valor para chave null");
        } catch (Exception e) {
            System.out.println("CustomMap com chave null: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}
