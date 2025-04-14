package com.interview.hashmapdemo.problem4;

import com.interview.hashmapdemo.HashMapProblem;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EqualsVsDoubleEqualsProblem implements HashMapProblem {
    @Override
    public String getProblemName() {
        return "Using == instead of .equals() for object keys";
    }

    @Override
    public String getProblemDescription() {
        return "Two logically equal keys are not matched in the map.\n" +
                "Cause: Manual lookup logic (e.g., iterating and using == instead of equals()).";
    }

    @Override
    public void demonstrate() {
        class Employee {
            private String id;
            private String name;

            public Employee(String id, String name) {
                this.id = id;
                this.name = name;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                Employee employee = (Employee) obj;
                return Objects.equals(id, employee.id);
            }

            @Override
            public int hashCode() {
                return Objects.hash(id);
            }

            @Override
            public String toString() {
                return id + ": " + name;
            }
        }

        HashMap<Employee, String> employees = new HashMap<>();

        Employee alice1 = new Employee("E001", "Alice");
        employees.put(alice1, "Department A");

        Employee alice2 = new Employee("E001", "Alice");

        // Correct: use HashMap's get() method
        System.out.println("Correct method (HashMap.get): " + employees.get(alice2));

        // Incorrect: iterate and compare using ==
        String incorrectResult = null;
        for (Map.Entry<Employee, String> entry : employees.entrySet()) {
            if (entry.getKey() == alice2) { // Using == instead of equals()
                incorrectResult = entry.getValue();
                break;
            }
        }
        System.out.println("Incorrect method (iteration with ==): " + incorrectResult);

        // Direct comparison using ==
        System.out.println("alice1 == alice2: " + (alice1 == alice2));
        System.out.println("alice1.equals(alice2): " + alice1.equals(alice2));
    }
}
