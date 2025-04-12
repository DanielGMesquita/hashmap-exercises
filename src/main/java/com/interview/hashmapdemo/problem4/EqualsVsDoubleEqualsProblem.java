package com.interview.hashmapdemo.problem4;

import com.interview.hashmapdemo.HashMapProblem;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EqualsVsDoubleEqualsProblem implements HashMapProblem {
    @Override
    public String getProblemName() {
        return "Usando == em vez de .equals() para chaves de objeto";
    }

    @Override
    public String getProblemDescription() {
        return "Duas chaves logicamente iguais não são correspondidas no mapa.\n" +
                "Causa: Lógica de pesquisa manual (por exemplo, iterar e usar == em vez de equals()).";
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
        employees.put(alice1, "Departamento A");

        Employee alice2 = new Employee("E001", "Alice");

        // correto: usar o get() do HashMap
        System.out.println("Método correto (HashMap.get): " + employees.get(alice2));

        // incorreto: iterar e comparar com ==
        String incorrectResult = null;
        for (Map.Entry<Employee, String> entry : employees.entrySet()) {
            if (entry.getKey() == alice2) { // Usando == em vez de equals()
                incorrectResult = entry.getValue();
                break;
            }
        }
        System.out.println("Método incorreto (iteração com ==): " + incorrectResult);

        // Verificação direta de ==
        System.out.println("alice1 == alice2: " + (alice1 == alice2));
        System.out.println("alice1.equals(alice2): " + alice1.equals(alice2));
    }
}
