# HashMap Problems Demo

Este projeto demonstra problemas comuns relacionados à implementação e uso de HashMaps em Java. Foi projetado para servir como exercício em entrevistas de codificação ao vivo, focando em situações problemáticas que frequentemente ocorrem ao trabalhar com HashMaps.

## 🧠 Conceitos Fundamentais

Esta seção explica os principais conceitos por trás do funcionamento interno de um `HashMap`, que são essenciais para entender os problemas demonstrados neste projeto.

### 🔹 Buckets

Um `HashMap` usa internamente um **array de "buckets"**, que são como "caixas" onde os pares chave-valor são armazenados.

- O índice do bucket é calculado com base no **código de hash da chave**.
- Exemplo: se `hashCode(key) = 42` e o array tem tamanho 16, o índice será `42 % 16 = 10`, então o par será colocado no bucket 10.
- Se dois objetos diferentes resultarem no mesmo índice, ambos ficam no mesmo bucket (isso é uma **colisão**).

### 🔹 `hashCode()` e `equals()`

Esses dois métodos da classe `Object` são fundamentais para o funcionamento de qualquer `Map` baseado em hash, como `HashMap`.

| Método        | Função                                                                                                                                 |
|---------------|----------------------------------------------------------------------------------------------------------------------------------------|
| `hashCode()`  | Retorna um número inteiro que representa o objeto. É usado para calcular **o índice do bucket**.                                       |
| `equals()`    | Compara se dois objetos são logicamente iguais. É usado para verificar se uma chave já existe dentro de um bucket.                     |

💡 **Contrato entre `equals()` e `hashCode()`**:
- Se dois objetos são iguais (`a.equals(b)` é `true`), **seus `hashCode()` devem ser iguais**.
- A violação desse contrato leva a comportamentos errados em `HashMap`.

### 🔹 Colisões

Uma **colisão** ocorre quando duas chaves diferentes resultam no mesmo índice de bucket.

- `HashMap` lida com colisões colocando múltiplos pares no mesmo bucket como uma **lista encadeada** ou, desde o Java 8, como uma **árvore balanceada** se o número de colisões for alto.
- Isso torna a busca mais lenta, pois é necessário percorrer todos os itens do bucket.

### 🔹 Complexidade de Tempo: O(1) vs O(n)

- `O(1)` (tempo constante): é o **caso ideal**, onde o `HashMap` pode localizar diretamente a chave no bucket correto sem precisar percorrer nada.
- `O(n)` (tempo linear): é o **pior caso**, quando há muitas colisões e o bucket contém uma longa lista (ou árvore), sendo necessário verificar cada elemento.

🎯 O objetivo de um bom `HashMap` é **minimizar colisões** e manter a complexidade próxima de O(1).

### 🔹 Imutabilidade das Chaves

Modificar um objeto após usá-lo como chave em um `HashMap` pode causar seu "desaparecimento".

- Isso ocorre porque o bucket onde ele foi inserido inicialmente depende de seu `hashCode()` no momento da inserção.
- Se você altera um atributo que afeta o `hashCode()`, ele passa a "pertencer" a outro bucket, mas o `HashMap` não atualiza isso.

💡 **Boa prática**: Use **objetos imutáveis** como chaves, como `String`, `Integer`, `UUID`.

### 🔹 `==` vs `.equals()`

- `==` compara **referências**: verifica se dois objetos apontam para o mesmo espaço na memória.
- `.equals()` compara **conteúdo**: verifica se dois objetos são logicamente equivalentes, conforme definido pela classe.

Usar `==` em vez de `.equals()` ao buscar objetos no mapa (ou ao sobrescrever `equals()` de forma incorreta) causa falhas silenciosas e bugs difíceis de detectar.

## Visão Geral dos Problemas

O projeto contém seis problemas comuns de HashMap:

1. **Implementação incorreta de `hashCode()` ou `equals()`**  
   Problema: Objetos usados como chaves em um HashMap não são encontrados mesmo quando parecem logicamente iguais.  
   Causa: A classe personalizada sobrescreve apenas um dos métodos `hashCode()` ou `equals()`, ou o faz incorretamente.  
   Impacto: O HashMap não consegue recuperar um objeto porque sua lógica depende da implementação correta de ambos os métodos.

2. **Mutando objetos de chave após inserção**  
   Problema: Um objeto chave é modificado após ser colocado no HashMap, e futuras pesquisas usando o mesmo objeto falham.  
   Causa: O código hash da chave muda após a inserção, então acaba no bucket errado.  
   Impacto: O objeto se torna inacessível pela busca normal, efetivamente "perdido" no mapa.

3. **Colisões de hash e má distribuição**  
   Problema: Múltiplas chaves são colocadas no mesmo bucket, degradando o desempenho de O(1) para O(n).  
   Causa: Uma função `hashCode()` mal projetada (por exemplo, sempre retornando o mesmo valor).  
   Impacto: Operações em HashMap se tornam significativamente mais lentas devido à necessidade de pesquisa linear em listas vinculadas dentro de um bucket.

4. **Usando `==` em vez de `.equals()` para chaves de objeto**  
   Problema: Duas chaves logicamente iguais não são correspondidas no mapa.  
   Causa: Lógica de pesquisa manual (por exemplo, iterar e usar `==` em vez de `equals()`).  
   Impacto: Comparações falham porque `==` compara referências de objeto, não seus valores lógicos.

5. **Modificação concorrente**  
   Problema: Um HashMap é modificado por múltiplas threads, causando corrupção de dados ou loops infinitos.  
   Causa: HashMap não é thread-safe.  
   Impacto: Comportamento imprevisível, incluindo perda de dados, loops infinitos ou exceções `ConcurrentModificationException`.

6. **Problemas com chaves `null`**  
   Problema: Chaves `null` causam comportamento inesperado ou exceções em várias implementações de Map.  
   Causa: Nem todas as implementações de `Map` suportam chaves `null` (`HashMap` permite, mas `Hashtable` e algumas implementações personalizadas não).  
   Impacto: `NullPointerException` ou outros comportamentos indefinidos quando `null` é usado como chave.

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
                        ├── BrokenHashMapDemo.java          # Classe principal
                        ├── HashMapProblem.java             # Interface comum
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

- 🔍 **Análise de código**: Peça ao candidato para identificar os problemas no código.
- 🛠 **Resolução de problemas**: Peça ao candidato para corrigir os problemas encontrados.
- 🧑‍🏫 **Discussão conceitual**: Use o código como base para discutir estruturas de dados, hashing e boas práticas de programação.

## Boas Práticas para HashMap

Com base nos problemas demonstrados, aqui estão algumas boas práticas a seguir:

- ✅ Sempre implemente `hashCode()` e `equals()` juntos quando criar classes usadas como chaves em `HashMap`.
- 🚫 Não modifique objetos usados como chaves após inseri-los em um `HashMap`.
- 🧮 Crie funções de hash que distribuam valores uniformemente para evitar colisões.
- ☑️ Use sempre o método `.equals()` para comparação de objetos, não o operador `==`.
- 🧵 Use `ConcurrentHashMap` para cenários multi-thread em vez de `HashMap`.
- ⚠️ Tenha cuidado com chaves `null` e saiba qual implementação de `Map` você está usando.

## Contribuições

Sinta-se à vontade para contribuir com exemplos adicionais ou aprimoramentos da base de código existente.