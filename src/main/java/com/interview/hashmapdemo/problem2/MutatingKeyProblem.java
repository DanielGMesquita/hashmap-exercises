package com.interview.hashmapdemo.problem2;

import com.interview.hashmapdemo.HashMapProblem;

import java.util.HashMap;
import java.util.Objects;

public class MutatingKeyProblem implements HashMapProblem {
    @Override
    public String getProblemName() {
        return "Mutando objetos de chave após inserção";
    }

    @Override
    public String getProblemDescription() {
        return "Um objeto chave é modificado após ser colocado no HashMap, " +
                "e futuras pesquisas usando o mesmo objeto falham.\n" +
                "Causa: O código hash da chave muda após a inserção, " +
                "então acaba no bucket errado.";
    }

    @Override
    public void demonstrate() {
        class MutableKey {
            private String value;

            public MutableKey(String value) {
                this.value = value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                MutableKey that = (MutableKey) obj;
                return Objects.equals(value, that.value);
            }

            @Override
            public int hashCode() {
                return Objects.hash(value);
            }

            @Override
            public String toString() {
                return "MutableKey{" + value + '}';
            }
        }

        HashMap<MutableKey, String> map = new HashMap<>();

        MutableKey key = new MutableKey("original");
        map.put(key, "valor original");

        System.out.println("Valor antes da mutação: " + map.get(key));

        // Mudar a chave após inserção
        key.setValue("modificado");
        System.out.println("Valor após mutação: " + map.get(key));

        // Criar uma nova chave com o valor original
        MutableKey originalKey = new MutableKey("original");
        System.out.println("Consegue recuperar com chave equivalente ao valor original? " +
                (map.get(originalKey) != null));

        // Demonstração de onde a chave modificada foi parar
        System.out.println("Tamanho do mapa: " + map.size());
        System.out.println("Conteúdo do mapa: " + map);
    }
}
