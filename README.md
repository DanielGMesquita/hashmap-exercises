# HashMap Problems Demo

Este projeto demonstra **problemas comuns relacionados à implementação e uso de `HashMap` em Java**. Foi projetado para ser utilizado como **exercício em entrevistas de codificação ao vivo**, servindo tanto para avaliação técnica quanto para discussão conceitual sobre estruturas de dados e boas práticas de programação.

## 🔍 Visão Geral do `HashMap` em Java

O `HashMap` é uma estrutura de dados da biblioteca padrão Java que **implementa a interface `Map`**, permitindo o armazenamento de pares chave-valor (`key-value`). Sua principal característica é o acesso rápido aos dados, com **complexidade média O(1)** para operações de inserção, remoção e busca.

Internamente, o `HashMap` funciona por meio de:

- **Tabela hash**: Um array de "buckets", onde cada bucket pode armazenar múltiplas entradas.
- **Função `hashCode()`**: Utilizada para determinar o bucket onde uma chave deve ser colocada.
- **Método `equals()`**: Utilizado para comparar chaves logicamente dentro de um bucket.
- **Tratamento de colisões**: Se múltiplas chaves tiverem o mesmo hash, elas são armazenadas no mesmo bucket por meio de uma lista encadeada ou uma árvore balanceada (desde o Java 8, quando o número de colisões ultrapassa um limiar).

**Desempenho ideal** depende de uma **boa função de espalhamento (`hashCode`)**, além do **respeito ao contrato entre `hashCode()` e `equals()`**.

---

## ⚠️ Visão Geral dos Problemas Demonstrados

Este projeto contém **seis classes de problemas recorrentes** em implementações com `HashMap`. Cada um deles destaca armadilhas comuns que podem impactar diretamente a correção e o desempenho da aplicação.

### 1. Implementação Incorreta de `hashCode()` ou `equals()`

**Problema**: Objetos usados como chaves não são encontrados, mesmo quando parecem iguais.

- **Causa**: A classe sobrescreve apenas `equals()` ou `hashCode()`, ou os implementa incorretamente.
- **Consequência**: O `HashMap` não consegue localizar a entrada, pois depende dos dois métodos estarem corretamente definidos.

📌 **Contrato de igualdade**:
- Se `a.equals(b)` for `true`, então `a.hashCode() == b.hashCode()` **deve** ser verdadeiro.

---

### 2. Mutação de Chaves Após Inserção

**Problema**: Um objeto usado como chave é alterado após ter sido inserido no `HashMap`, tornando-o irreconhecível na busca posterior.

- **Causa**: A alteração afeta atributos usados no cálculo de `hashCode()` ou `equals()`.
- **Consequência**: O objeto "desaparece" do mapa, pois sua chave original não é mais válida.

📌 **Boa prática**: Use **objetos imutáveis como chaves** sempre que possível.

---

### 3. Colisões de Hash e Má Distribuição

**Problema**: Muitas chaves diferentes são mapeadas para o mesmo bucket.

- **Causa**: Função `hashCode()` mal projetada, como sempre retornar o mesmo valor.
- **Consequência**: O desempenho se degrada para **O(n)** em vez de O(1), pois as buscas percorrem listas ou árvores dentro de um bucket.

📌 **Impacto real**: Com grandes volumes de dados, pode haver **problemas de performance severos**.

---

### 4. Uso de `==` em vez de `.equals()` na Comparação de Chaves

**Problema**: Chaves logicamente equivalentes não são consideradas iguais durante busca manual.

- **Causa**: O uso do operador `==`, que compara **referências de memória**, em vez de `.equals()`, que compara conteúdo lógico.
- **Consequência**: A busca falha mesmo que os objetos representem a mesma informação.

📌 **Exemplo clássico**: Comparar duas `String` com `==` pode dar `false`, mesmo que os textos sejam idênticos.

---

### 5. Modificação Concorrente

**Problema**: Um `HashMap` é acessado/modificado por múltiplas threads simultaneamente sem sincronização.

- **Causa**: O `HashMap` **não é thread-safe**.
- **Consequência**: Corrupção de dados, exceções (`ConcurrentModificationException`), ou loops infinitos.

📌 **Soluções**:
- Use `Collections.synchronizedMap(...)` para sincronizar.
- Use `ConcurrentHashMap` para performance e segurança em ambientes multithread.

---

### 6. Problemas com Chaves `null`

**Problema**: Uso de `null` como chave leva a exceções ou comportamento indefinido.

- **Causa**: Embora `HashMap` aceite uma única chave `null`, **outras implementações de `Map` como `Hashtable` não aceitam**.
- **Consequência**: Pode ocorrer `NullPointerException` ou falhas inesperadas ao trocar a implementação do `Map`.

📌 **Boa prática**: Evitar chaves `null` sempre que possível.

---

## ⚙️ Requisitos

- Java 11 ou superior
- Maven

---

## 🚀 Como Executar

### Compilar o Projeto

```bash
mvn clean compile
