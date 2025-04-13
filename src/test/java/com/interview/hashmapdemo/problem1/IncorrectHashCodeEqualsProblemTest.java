package com.interview.hashmapdemo.problem1;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Objects;

public class IncorrectHashCodeEqualsProblemTest {

    // Test case 1: Missing hashCode() implementation
    // Expected: Objects are equal but HashMap can't find them due to missing hashCode()
    @Test
    void testMissingHashCodeImplementation() {
        class PersonBadHashCode {
            private final String name;
            private final int age;

            public PersonBadHashCode(String name, int age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                PersonBadHashCode person = (PersonBadHashCode) obj;
                return age == person.age && name.equals(person.name);
            }
        }

        HashMap<PersonBadHashCode, String> map = new HashMap<>();
        PersonBadHashCode john1 = new PersonBadHashCode("John", 30);
        PersonBadHashCode john2 = new PersonBadHashCode("John", 30);
        
        map.put(john1, "Developer");
        
        assertTrue(john1.equals(john2), "Objects should be equal");
        assertFalse(map.containsKey(john2), "HashMap should not find the object due to missing hashCode()");
        assertNull(map.get(john2), "HashMap should not return the value for the equal object");
    }

    // Test case 2: Inconsistent hashCode() implementation
    // Expected: HashMap becomes inconsistent when hashCode() returns different values for equal objects
    @Test
    void testInconsistentHashCode() {
        class PersonInconsistentHashCode {
            private final String name;
            private final int age;
            private static int nextHash = 0;
            private final int myHashCode;

            public PersonInconsistentHashCode(String name, int age) {
                this.name = name;
                this.age = age;
                this.myHashCode = nextHash++;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                PersonInconsistentHashCode person = (PersonInconsistentHashCode) obj;
                return age == person.age && name.equals(person.name);
            }

            @Override
            public int hashCode() {
                return myHashCode; // Different hash code each time
            }
        }

        HashMap<PersonInconsistentHashCode, String> map = new HashMap<>();
        PersonInconsistentHashCode person1 = new PersonInconsistentHashCode("John", 30);
        int hashcode1 = person1.hashCode();
        PersonInconsistentHashCode person2 = new PersonInconsistentHashCode("John", 30);
        int hashcode2 = person2.hashCode();
        
        map.put(person1, "Developer");
        assertNotEquals(hashcode1, hashcode2);
        assertNull(map.get(person2), "HashMap should not find the object due to inconsistent hashCode()");
    }

    // Test case 3: Incorrect equals() implementation
    // Expected: HashMap becomes inconsistent when equals() is not properly implemented
    @Test
    void testIncorrectEquals() {
        class PersonIncorrectEquals {
            private String name;
            private int age;

            public PersonIncorrectEquals(String name, int age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                PersonIncorrectEquals person = (PersonIncorrectEquals) obj;
                return age == person.age; // Only comparing age, ignoring name
            }

            @Override
            public int hashCode() {
                return Objects.hash(name, age);
            }
        }

        HashMap<PersonIncorrectEquals, String> map = new HashMap<>();
        PersonIncorrectEquals person1 = new PersonIncorrectEquals("John", 30);
        PersonIncorrectEquals person2 = new PersonIncorrectEquals("Mary", 30);
        
        map.put(person1, "Developer");
        assertNotEquals("Developer", map.get(person2),
            "HashMap should find the object due to incorrect equals() implementation");
    }

    // Test case 4: Correct implementation of both hashCode() and equals()
    // Expected: HashMap works correctly with proper implementations
    @Test
    void testCorrectImplementation() {
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
                return age == person.age && name.equals(person.name);
            }

            @Override
            public int hashCode() {
                return Objects.hash(name, age);
            }
        }

        HashMap<PersonCorrect, String> map = new HashMap<>();
        PersonCorrect person1 = new PersonCorrect("John", 30);
        PersonCorrect person2 = new PersonCorrect("John", 30);
        PersonCorrect person3 = new PersonCorrect("Mary", 30);
        
        map.put(person1, "Developer");
        assertEquals("Developer", map.get(person2), 
            "HashMap should find the equal object");
        assertNull(map.get(person3), 
            "HashMap should not find the different object");
    }

    // Test case 5: Inheritance and equals() contract
    // Expected: Symmetry and transitivity of equals() should be maintained
    @Test
    void testEqualsContract() {
        class Person {
            private String name;
            private int age;

            public Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                Person person = (Person) obj;
                return age == person.age && name.equals(person.name);
            }

            @Override
            public int hashCode() {
                return Objects.hash(name, age);
            }
        }

        class Employee extends Person {
            private String department;

            public Employee(String name, int age, String department) {
                super(name, age);
                this.department = department;
            }

            @Override
            public boolean equals(Object obj) {
                if (!super.equals(obj)) return false;
                if (obj == null || getClass() != obj.getClass()) return false;
                Employee employee = (Employee) obj;
                return department.equals(employee.department);
            }

            @Override
            public int hashCode() {
                return Objects.hash(super.hashCode(), department);
            }
        }

        Person person = new Person("John", 30);
        Employee employee = new Employee("John", 30, "IT");
        
        assertFalse(person.equals(employee), 
            "Person should not equal Employee (symmetry violation)");
        assertFalse(employee.equals(person), 
            "Employee should not equal Person (symmetry violation)");
    }

    // Test case 6: Null handling in equals() and hashCode()
    // Expected: Proper handling of null values
    @Test
    void testNullHandling() {
        class PersonWithNull {
            private String name;
            private Integer age;

            public PersonWithNull(String name, Integer age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                PersonWithNull person = (PersonWithNull) obj;
                return (age == null ? person.age == null : age.equals(person.age)) &&
                       (name == null ? person.name == null : name.equals(person.name));
            }

            @Override
            public int hashCode() {
                return Objects.hash(name, age);
            }
        }

        HashMap<PersonWithNull, String> map = new HashMap<>();
        PersonWithNull person1 = new PersonWithNull(null, null);
        PersonWithNull person2 = new PersonWithNull(null, null);
        PersonWithNull person3 = new PersonWithNull("John", 30);
        
        map.put(person1, "Unknown");
        assertEquals("Unknown", map.get(person2), 
            "HashMap should handle null values correctly");
        assertNull(map.get(person3), 
            "HashMap should not find different object");
    }
} 