package com.interview.hashmapdemo;

public class BrokenHashMapDemo {
    public static void main(String[] args) {
        System.out.println("=== Demonstração de Problemas Comuns de HashMap ===\n");

        // Se for passado um argumento, executar apenas o problema específico
        if (args.length > 0) {
            try {
                int problemNumber = Integer.parseInt(args[0]);
                runSpecificProblem(problemNumber);
            } catch (NumberFormatException e) {
                System.out.println("Argumento deve ser um número entre 1 e 6.");
            }
        } else {
            // Executar todos os problemas
            runAllProblems();
        }
    }

    private static void runSpecificProblem(int problemNumber) {
        HashMapProblem problem = getProblem(problemNumber);
        if (problem != null) {
            System.out.println(problem.getProblemName());
            System.out.println(problem.getProblemDescription());
            System.out.println("---");
            problem.demonstrate();
        } else {
            System.out.println("Problema " + problemNumber + " não encontrado. Use um número entre 1 e 6.");
        }
    }

    private static void runAllProblems() {
        for (int i = 1; i <= 6; i++) {
            HashMapProblem problem = getProblem(i);
            if (problem != null) {
                System.out.println(i + ". " + problem.getProblemName());
                System.out.println(problem.getProblemDescription());
                System.out.println("---");
                problem.demonstrate();
                System.out.println("\n");
            }
        }
    }

    private static HashMapProblem getProblem(int problemNumber) {
        switch (problemNumber) {
            case 1:
                return new com.interview.hashmapdemo.problem1.IncorrectHashCodeEqualsProblem();
            case 2:
                return new com.interview.hashmapdemo.problem2.MutatingKeyProblem();
            case 3:
                return new com.interview.hashmapdemo.problem3.HashCollisionProblem();
            case 4:
                return new com.interview.hashmapdemo.problem4.EqualsVsDoubleEqualsProblem();
            case 5:
                return new com.interview.hashmapdemo.problem5.ConcurrentModificationProblem();
            case 6:
                return new com.interview.hashmapdemo.problem6.NullKeyProblem();
            default:
                return null;
        }
    }
}
