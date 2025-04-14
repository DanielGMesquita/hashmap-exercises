package com.interview.hashmapdemo.problem1;

import com.interview.hashmapdemo.HashMapProblem;

import java.util.HashMap;
import java.util.Objects;

public class IncorrectHashCodeEqualsProblem implements HashMapProblem {
    @Override
    public String getProblemName() {
        return "Incorrect HashCode and Equals Implementation";
    }

    @Override
    public String getProblemDescription() {
        return "Objects with the same logical value are not matched in the map.\n" +
                "Cause: Incorrect implementation of equals() and hashCode().\n" +
                "This can lead to unexpected behavior when using HashMap, " +
                "as it relies on these methods to determine object equality.";
    }

    @Override
    public void demonstrate() {
        // Class with incorrect implementation of equals() and hashCode() - only equals() is implemented
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

            @Override
            public String toString() {
                return name + " (" + age + ")";
            }
        }

        // Class with correct implementation of equals() and hashCode()
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

        // Incorrectly implemented class
        HashMap<PersonBadHashCode, String> badMap = new HashMap<>();
        PersonBadHashCode john1 = new PersonBadHashCode("John", 30);
        badMap.put(john1, "Developer");

        PersonBadHashCode john2 = new PersonBadHashCode("John", 30);
        System.out.println("john1.equals(john2): " + john1.equals(john2));
        System.out.println("badMap.containsKey(john2): " + badMap.containsKey(john2));
        System.out.println("Valor para john2: " + badMap.get(john2));

        // Correctly implemented class
        HashMap<PersonCorrect, String> goodMap = new HashMap<>();
        PersonCorrect mary1 = new PersonCorrect("Mary", 28);
        goodMap.put(mary1, "Designer");

        PersonCorrect mary2 = new PersonCorrect("Mary", 28);
        System.out.println("mary1.equals(mary2): " + mary1.equals(mary2));
        System.out.println("goodMap.containsKey(mary2): " + goodMap.containsKey(mary2));
        System.out.println("Valor para mary2: " + goodMap.get(mary2));
    }
}
