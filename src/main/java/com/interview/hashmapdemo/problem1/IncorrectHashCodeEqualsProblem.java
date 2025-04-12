package com.interview.hashmapdemo.problem1;

import com.interview.hashmapdemo.HashMapProblem;

import java.util.HashMap;
import java.util.Objects;

public class IncorrectHashCodeEqualsProblem implements HashMapProblem {
    @Override
    public String getProblemName() {
        return "Implementação incorreta de hashCode() ou equals()";
    }

    @Override
    public String getProblemDescription() {
        return "Objetos usados como chaves em um HashMap não são encontrados " +
                "mesmo quando parecem logicamente iguais.\n" +
                "Causa: A classe personalizada sobrescreve apenas um dos métodos " +
                "hashCode() ou equals(), ou o faz incorretamente.";
    }

    @Override
    public void demonstrate() {
        // Classe com implementação incorreta - apenas equals() sobrescrito
        class PersonBadHashCode {
            private String name;
            private int age;

            public PersonBadHashCode(String name, int age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                PersonBadHashCode person = (PersonBadHashCode) obj;
                return age == person.age &&
                        Objects.equals(name, person.name);
            }

            // Falta implementação de hashCode()!

            @Override
            public String toString() {
                return name + " (" + age + ")";
            }
        }

        // Classe com implementação correta
        class PersonCorrect {
            private String name;
            private int age;

            public PersonCorrect(String name, int age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                PersonCorrect person = (PersonCorrect) obj;
                return age == person.age &&
                        Objects.equals(name, person.name);
            }

            @Override
            public int hashCode() {
                return Objects.hash(name, age);
            }

            @Override
            public String toString() {
                return name + " (" + age + ")";
            }
        }

        // Demonstração com implementação incorreta
        HashMap<PersonBadHashCode, String> badMap = new HashMap<>();
        PersonBadHashCode john1 = new PersonBadHashCode("John", 30);
        badMap.put(john1, "Developer");

        PersonBadHashCode john2 = new PersonBadHashCode("John", 30);
        System.out.println("john1.equals(john2): " + john1.equals(john2));
        System.out.println("badMap.containsKey(john2): " + badMap.containsKey(john2));
        System.out.println("Valor para john2: " + badMap.get(john2));

        // Demonstração com implementação correta
        HashMap<PersonCorrect, String> goodMap = new HashMap<>();
        PersonCorrect mary1 = new PersonCorrect("Mary", 28);
        goodMap.put(mary1, "Designer");

        PersonCorrect mary2 = new PersonCorrect("Mary", 28);
        System.out.println("mary1.equals(mary2): " + mary1.equals(mary2));
        System.out.println("goodMap.containsKey(mary2): " + goodMap.containsKey(mary2));
        System.out.println("Valor para mary2: " + goodMap.get(mary2));
    }
}
