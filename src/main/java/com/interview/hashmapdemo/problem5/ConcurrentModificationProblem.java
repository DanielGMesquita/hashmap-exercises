package com.interview.hashmapdemo.problem5;

import com.interview.hashmapdemo.HashMapProblem;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentModificationProblem implements HashMapProblem {
    @Override
    public String getProblemName() {
        return "Modificação concorrente";
    }

    @Override
    public String getProblemDescription() {
        return "Um HashMap é modificado por múltiplas threads, causando " +
                "corrupção de dados ou loops infinitos.\n" +
                "Causa: HashMap não é thread-safe.";
    }

    @Override
    public void demonstrate() {
        // HashMap não seguro para threads
        final Map<Integer, String> unsafeMap = new HashMap<>();

        // HashMap seguro para threads
        final Map<Integer, String> safeMap = new ConcurrentHashMap<>();

        // Preencher inicialmente
        for (int i = 0; i < 100; i++) {
            unsafeMap.put(i, "Valor " + i);
            safeMap.put(i, "Valor " + i);
        }

        // Modificar a partir de várias threads
        ExecutorService executor = Executors.newFixedThreadPool(10);

        System.out.println("Executando operações concorrentes...");

        try {
            // Modificações concorrentes no HashMap não seguro
            for (int i = 0; i < 10; i++) {
                final int base = i * 100;
                executor.submit(() -> {
                    try {
                        for (int j = 0; j < 100; j++) {
                            unsafeMap.put(base + j, "Thread " + base + "-" + j);
                            // Simular algum trabalho
                            Thread.sleep(1);
                        }
                    } catch (Exception e) {
                        System.out.println("Erro no unsafeMap: " + e.getMessage());
                    }
                });
            }

            // Leitura concorrente
            executor.submit(() -> {
                try {
                    for (Map.Entry<Integer, String> entry : unsafeMap.entrySet()) {
                        // Apenas leitura
                        String value = entry.getValue();
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao iterar unsafeMap: " + e.getMessage());
                }
            });

            // Mesmo padrão com ConcurrentHashMap
            for (int i = 0; i < 10; i++) {
                final int base = i * 100;
                executor.submit(() -> {
                    try {
                        for (int j = 0; j < 100; j++) {
                            safeMap.put(base + j, "Thread " + base + "-" + j);
                            // Simular algum trabalho
                            Thread.sleep(1);
                        }
                    } catch (Exception e) {
                        System.out.println("Erro no safeMap: " + e.getMessage());
                    }
                });
            }

            // Leitura do map seguro
            executor.submit(() -> {
                try {
                    for (Map.Entry<Integer, String> entry : safeMap.entrySet()) {
                        // Apenas leitura
                        String value = entry.getValue();
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao iterar safeMap: " + e.getMessage());
                    e.printStackTrace();
                }
            });

            // Aguardar a conclusão ou timeout
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);

        } catch (Exception e) {
            System.out.println("Exceção principal: " + e.getMessage());
        }

        System.out.println("Tamanho do unsafeMap após concorrência: " + unsafeMap.size());
        System.out.println("Tamanho do safeMap após concorrência: " + safeMap.size());
    }
}
