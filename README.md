# HashMap Problems Demo

Este projeto demonstra problemas comuns relacionados à implementação e uso de HashMaps em Java. Foi projetado para servir como exercício em entrevistas de codificação ao vivo, focando em situações problemáticas que frequentemente ocorrem ao trabalhar com HashMaps.

## Visão Geral dos Problemas

O projeto contém seis problemas comuns de HashMap:

### 1. Implementação incorreta de hashCode() ou equals()
**Problema:** Objetos usados como chaves em um HashMap não são encontrados mesmo quando parecem logicamente iguais.  
**Causa:** A classe personalizada sobrescreve apenas um dos métodos hashCode() ou equals(), ou o faz incorretamente.  
**Impacto:** O HashMap não consegue recuperar um objeto porque sua lógica depende da implementação correta de ambos os métodos.

### 2. Mutando objetos de chave após inserção
**Problema:** Um objeto chave é modificado após ser colocado no HashMap, e futuras pesquisas usando o mesmo objeto falham.  
**Causa:** O código hash da chave muda após a inserção, então acaba no bucket errado.  
**Impacto:** O objeto se torna inacessível pela busca normal, efetivamente "perdido" no mapa.

### 3. Colisões de hash e má distribuição
**Problema:** Múltiplas chaves são colocadas no mesmo bucket, degradando o desempenho de O(1) para O(n).  
**Causa:** Uma função hashCode() mal projetada (por exemplo, sempre retornando o mesmo valor).  
**Impacto:** Operações em HashMap se tornam significativamente mais lentas devido à necessidade de pesquisa linear em listas vinculadas dentro de um bucket.

### 4. Usando == em vez de .equals() para chaves de objeto
**Problema:** Duas chaves logicamente iguais não são correspondidas no mapa.  
**Causa:** Lógica de pesquisa manual (por exemplo, iterar e usar == em vez de equals()).  
**Impacto:** Comparações falham porque == compara referências de objeto, não seus valores lógicos.

### 5. Modificação concorrente
**Problema:** Um HashMap é modificado por múltiplas threads, causando corrupção de dados ou loops infinitos.  
**Causa:** HashMap não é thread-safe.  
**Impacto:** Comportamento imprevisível, incluindo perda de dados, loops infinitos ou exceções ConcurrentModificationException.

### 6. Problemas com chaves null
**Problema:** Chaves null causam comportamento inesperado ou exceções em várias implementações de Map.  
**Causa:** Nem todas as implementações de Map suportam chaves null (HashMap permite, mas Hashtable e algumas implementações personalizadas não).  
**Impacto:** NullPointerException ou outros comportamentos indefinidos quando null é usado como chave.

## Requisitos

- Java 11 ou superior
- Maven

## Como Executar

### Compilar o Projeto
```bash
mvn clean compile
```

### Executar Todos os Problemas
```bash
java -cp target/classes com.interview.hashmapdemo.BrokenHashMapDemo
```

### Executar um Problema Específico
```bash
java -cp target/classes com.interview.hashmapdemo.BrokenHashMapDemo <número>
```
Onde `<número>` é o número do problema (1-6) que você deseja demonstrar.

Exemplo para executar o problema de colisões de hash:
```bash
java -cp target/classes com.interview.hashmapdemo.BrokenHashMapDemo 3
```

## Estrutura do Projeto

```
hashmap-demo/
├── pom.xml
└── src/
    └── main/
        └── java/
            └── com/
                └── interview/
                    └── hashmapdemo/
                        ├── BrokenHashMapDemo.java (classe principal)
                        ├── HashMapProblem.java (interface comum)
                        ├── problem1/
                        │   └── IncorrectHashCodeEqualsProblem.java
                        ├── problem2/
                        │   └── MutatingKeyProblem.java
                        ├── problem3/
                        │   └── HashCollisionProblem.java
                        ├── problem4/
                        │   └── EqualsVsDoubleEqualsProblem.java
                        ├── problem5/
                        │   └── ConcurrentModificationProblem.java
                        └── problem6/
                            └── NullKeyProblem.java
```

## Uso em Entrevistas de Codificação

Este projeto é ideal para entrevistas de codificação ao vivo, pois permite:

1. **Análise de código**: Peça ao candidato para identificar os problemas no código.
2. **Resolução de problemas**: Peça ao candidato para corrigir os problemas encontrados.
3. **Discussão conceitual**: Use o código como base para discutir estruturas de dados, hashing e boas práticas de programação.

## Boas Práticas para HashMap

Com base nos problemas demonstrados, aqui estão algumas boas práticas a seguir:

1. **Sempre implemente hashCode() e equals() juntos** quando criar classes usadas como chaves em HashMap.
2. **Não modifique objetos usados como chaves** após inseri-los em um HashMap.
3. **Crie funções de hash que distribuam valores uniformemente** para evitar colisões.
4. **Use sempre o método .equals()** para comparação de objetos, não o operador ==.
5. **Use ConcurrentHashMap** para cenários multi-thread em vez de HashMap.
6. **Tenha cuidado com chaves null** e saiba qual implementação de Map você está usando.

## Contribuições

Sinta-se à vontade para contribuir com exemplos adicionais ou aprimoramentos da base de código existente.